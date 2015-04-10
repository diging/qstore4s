package edu.asu.qstore4s.db.neo4j.impl;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.qstore4s.converter.IXmlElements;
import edu.asu.qstore4s.db.neo4j.IStoreObjectsToDb;
import edu.asu.qstore4s.domain.elements.IElement;
import edu.asu.qstore4s.domain.elements.IRelation;
import edu.asu.qstore4s.domain.elements.ITerm;
import edu.asu.qstore4s.domain.elements.ITermPart;
import edu.asu.qstore4s.domain.elements.ITermParts;
import edu.asu.qstore4s.domain.events.IAppellationEvent;
import edu.asu.qstore4s.domain.events.ICreationEvent;
import edu.asu.qstore4s.domain.events.IRelationEvent;
import edu.asu.qstore4s.domain.events.impl.AppellationEvent;
import edu.asu.qstore4s.domain.events.impl.RelationEvent;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.repository.AppellationEventRepository;
import edu.asu.qstore4s.repository.RelationEventRepository;
import edu.asu.qstore4s.repository.RelationRepository;
import edu.asu.qstore4s.repository.TermPartRepository;
import edu.asu.qstore4s.repository.TermPartsRepository;
import edu.asu.qstore4s.repository.TermRepository;

/**
 * This class stores the objects created out of the input string to database.
 * 
 * @author Veena Borannagowda, Bhargav Desai
 * 
 */
@PropertySource(value = "classpath:/qstore4s.properties")
@Service
public class StoreObjectsToDb implements IStoreObjectsToDb {

	@Autowired
	private Environment env;

	@Autowired
	private AppellationEventRepository appellationEventRepository;

	@Autowired
	private RelationEventRepository relationEventRepository;

	@Autowired
	private RelationRepository relationRepository;

	@Autowired
	private TermRepository termRepository;

	@Autowired
	private TermPartRepository termPartRepository;

	@Autowired
	private TermPartsRepository termPartsRepository;

	@Autowired
	private Neo4jOperations template;

	// @RequestMapping(value="/NEO4J_SERVER_URL");

//	private static final Logger logger = LoggerFactory
//			.getLogger(StoreObjectsToDb.class);


/**
 * {@inheritDoc}
 */

	@Transactional
	public List<ICreationEvent> writeObjectsToDb(
			List<List<IElement>> creationEventList) throws URISyntaxException,
			InvalidDataException {

		List<ICreationEvent> newCreationEventList = new ArrayList<ICreationEvent>();
		List<IElement> creationEventObjects = (creationEventList.get(0));
		List<IElement> referencedList = creationEventList.get(1);
		Iterator<IElement> creationEventIterator = creationEventObjects
				.iterator();
		while (creationEventIterator.hasNext()) {
			ICreationEvent creationEventObject = (ICreationEvent) (creationEventIterator
					.next());

			if (creationEventObject instanceof AppellationEvent) {
				IAppellationEvent appellation = getAppellationObject(
						(IAppellationEvent) creationEventObject, referencedList);

				appellationEventRepository.save((AppellationEvent) appellation);
				newCreationEventList.add(appellation);

			} else if (creationEventObject instanceof RelationEvent) {
				IRelationEvent relation = getRelationEventObject(
						(IRelationEvent) creationEventObject, referencedList);

				relationEventRepository.save((RelationEvent) relation);
				newCreationEventList.add(relation);

			}
			//newCreationEventList.add(creationEventObject);

		}

		return newCreationEventList;

	}

	/**
	 * This method stores relationObject into the database.
	 * 
	 * @param relationEventObject
	 *            the relation event object to store in the db.
	 * @param termsList
	 *            the list of the terms already stored in the db.
	 * @return URI of the node in the database.
	 */

	public IRelationEvent getRelationEventObject(
			IRelationEvent relationEventObject, List<IElement> referencedList)
					throws URISyntaxException, InvalidDataException {
		
		int refFoundFlag=0;
		if (relationEventObject.getInternal_refId() != null
				&& !relationEventObject.getInternal_refId().trim().equals("")) {

			for (IElement element : referencedList) {
				if (element.getRefId().equals(
						relationEventObject.getInternal_refId())) {
					
					String internalRefId= relationEventObject.getInternal_refId();
					
					relationEventObject = (IRelationEvent) element;
					
					
					relationEventObject.setInternal_refId(internalRefId);
					refFoundFlag = 1;
					//break;
					return relationEventObject;
				}
			}
			if(refFoundFlag == 0)
				throw new InvalidDataException("The referrenced relation event is not present");
			
		} else if (relationEventObject.getExternal_refId() != null
				&& !relationEventObject.getExternal_refId().trim().equals("")) {
			
			String externalRefId = relationEventObject.getExternal_refId();
			if((externalRefId.length() > IXmlElements.REL_EVENT_ID_PREFIX.length()) && externalRefId.substring(0, 7).equals(IXmlElements.REL_EVENT_ID_PREFIX))
			{
				relationEventObject = relationEventRepository
						.findById(relationEventObject.getExternal_refId());
				if (relationEventObject != null) {
					relationEventObject.setExternal_refId(externalRefId);
					return relationEventObject;
				} else {
					throw new InvalidDataException("Relation Event with "
							+ externalRefId + " not present in database.");
				}
			}
			else
			{
				throw new InvalidDataException("The given ID is not corresponding to RELATION_EVENT");
			}

		}
		if (relationEventObject.getId() == null
				|| relationEventObject.getId().trim().equals("")) {
			relationEventObject.setId(IXmlElements.REL_EVENT_ID_PREFIX
					+ UUID.randomUUID().getMostSignificantBits());
			relationEventObject.setIdAssigned(true);
		} else {
			if (!relationEventObject.isIdAssigned())
				throw new InvalidDataException(
						"The Id for the relation event is already assigned");
		}
		IRelation relationObject = relationEventObject.getRelation();

		relationObject = getRelationObjcet(relationObject, referencedList);

		relationEventObject.setRelation(relationObject);
		return relationEventObject;

	}

	/**
	 * This method stores relationObject 
	 * @param relationObject
	 * @param referencedList
	 * @return
	 * @throws InvalidDataException
	 * @throws URISyntaxException
	 */

	public IRelation getRelationObjcet(IRelation relationObject,
			List<IElement> referencedList) throws InvalidDataException,
			URISyntaxException {

		int refFoundFlag = 0;
		if (relationObject.getInternal_refId() != null
				&& !relationObject.getInternal_refId().trim().equals("")) {

			for (IElement element : referencedList) {
				if (element.getRefId().equals(
						relationObject.getInternal_refId())) {
					String internalRefId= relationObject.getInternal_refId();
					relationObject = (IRelation) element;
                    refFoundFlag = 1;
                    relationObject.setInternal_refId(internalRefId);
                    return relationObject;
				}
			}
			
			if(refFoundFlag == 0)
				throw new InvalidDataException("The referenced relation is not present");
			
		} else if (relationObject.getExternal_refId() != null
				&& !relationObject.getExternal_refId().trim().equals("")) {

			String externalRefId = relationObject.getExternal_refId();
			if((externalRefId.length() > IXmlElements.REL_ID_PREFIX.length()) && externalRefId.substring(0,3).equals(IXmlElements.REL_ID_PREFIX))
			{
				relationObject = relationRepository.findById(relationObject
						.getExternal_refId());
				if (relationObject != null) {
					relationObject.setExternal_refId(externalRefId);
					return relationObject;

				} else {
					throw new InvalidDataException("Relation  with "
							+ externalRefId + " not present in database.");
				}
			}
			else
			{
				throw new InvalidDataException("The given id is not corresponding to RELATION");
			}

		}

		if (relationObject.getId() == null
				|| relationObject.getId().trim().equals("")) {
			relationObject.setId(IXmlElements.REL_ID_PREFIX
					+ UUID.randomUUID().getMostSignificantBits());
			relationObject.setIdAssigned(true);
		} else {
			if (!relationObject.isIdAssigned())
				throw new InvalidDataException(
						"The Id for the relation event is already assigned");
		}

		{
			ICreationEvent relationChild = relationObject.getSubject();
			if (relationChild instanceof AppellationEvent) {
				IAppellationEvent subject = getAppellationObject(
						(IAppellationEvent) relationChild, referencedList);
				relationObject.setSubject(subject);
			} else if (relationChild instanceof RelationEvent) {
				IRelationEvent childRelationEvent = getRelationEventObject(
						(IRelationEvent) relationChild, referencedList);
				relationObject.setSubject(childRelationEvent);
			}
		}
		{
			ICreationEvent relationChild = relationObject.getPredicate();
			if (relationChild instanceof AppellationEvent) {
				IAppellationEvent predicate = getAppellationObject(
						(IAppellationEvent) relationChild, referencedList);
				relationObject.setPredicate(predicate);
			}

		}
		{
			ICreationEvent relationChild = relationObject.getObject();
			if (relationChild instanceof AppellationEvent) {
				IAppellationEvent object = getAppellationObject(
						(IAppellationEvent) relationChild, referencedList);
				relationObject.setObject(object);
			} else if (relationChild instanceof RelationEvent) {
				IRelationEvent childRelationEvent = getRelationEventObject(
						(IRelationEvent) relationChild, referencedList);
				relationObject.setObject(childRelationEvent);
			}
		}

		return relationObject;

	}

	/**
	 * This method stores appellationObject into the database.
	 * 
	 * @param appellationObject
	 *            the appellation event object to store in the db.
	 * @param termsList
	 *            the list of the terms already stored in the db.
	 * @return URI of the node in the database.
	 */
	public IAppellationEvent getAppellationObject(
			IAppellationEvent appellationObject, List<IElement> referencedList)
					throws URISyntaxException, InvalidDataException {

		int refFoundFlag = 0;
		if (appellationObject.getInternal_refId() != null
				&& !appellationObject.getInternal_refId().trim().equals("")) {
			for (IElement element : referencedList) {
				if (element.getRefId().equals(
						appellationObject.getInternal_refId())) {
					String internalRefID = appellationObject.getInternal_refId();
					appellationObject = (IAppellationEvent) element;
                    refFoundFlag = 1;
                    appellationObject.setInternal_refId(internalRefID);
                    return appellationObject;
				}
			}
			if(refFoundFlag == 0)
				throw new InvalidDataException("The referenced appellation event is not present");
			
		} else if (appellationObject.getExternal_refId() != null
				&& !appellationObject.getExternal_refId().trim().equals("")) {

			String externalRefId = appellationObject.getExternal_refId();
			if((externalRefId.length() > IXmlElements.APPELLATION_ID_PREFIX.length()) && externalRefId.substring(0, 7).equals(IXmlElements.APPELLATION_ID_PREFIX))
			{
				appellationObject = appellationEventRepository
						.findById(appellationObject.getExternal_refId());


				if (appellationObject != null) {
					appellationObject.setExternal_refId(externalRefId);
					return appellationObject;
				} else {
					throw new InvalidDataException("Appellation Event with "
							+ externalRefId + " not present in database.");
				}

			}
			else
			{
				throw new InvalidDataException("The given ID is not corresponding to APPELLATION");
			}
		}

		if (appellationObject.getId() == null
				|| appellationObject.getId().trim().equals("")) {
			appellationObject.setId(IXmlElements.APPELLATION_ID_PREFIX
					+ UUID.randomUUID().getMostSignificantBits());
			appellationObject.setIdAssigned(true);
		} else {
			if (!appellationObject.isIdAssigned())
				throw new InvalidDataException(
						"The Id for the appellation event is already assigned");
		}

		ITerm termObject = appellationObject.getTerm();
		ITerm termNode = getTermObject(termObject, referencedList);
		appellationObject.setTerm(termNode);

		return appellationObject;
	}

	/**
	 * This method stores term object into the database.
	 * 
	 * @param term
	 *            Object the term object to store in the database.
	 * @return URI of the node in the database.
	 */
	public ITerm getTermObject(ITerm termObject, List<IElement> referencedList)
			throws URISyntaxException, InvalidDataException {
		
		int refFoundFlag = 0;
		if (termObject.getInternal_refId() != null
				&& !termObject.getInternal_refId().trim().equals("")) {
			for (IElement element : referencedList) {
				if (element.getRefId().equals(termObject.getInternal_refId())
						&& element instanceof ITerm) {
					termObject = (ITerm) element;
					refFoundFlag = 1;
				}
			}
			if(refFoundFlag == 0)
				throw new InvalidDataException("The referenced term is not present");
			
		} else if (termObject.getExternal_refId() != null
				&& !termObject.getExternal_refId().trim().equals("")) {

			String externalRefId = termObject.getExternal_refId();
			if((externalRefId.length() > IXmlElements.TERM_ID_PERFIX.length()) && externalRefId.substring(0, 4).equals(IXmlElements.TERM_ID_PERFIX))
			{
				termObject = termRepository
						.findById(termObject.getExternal_refId()); 

				if (termObject != null) {
					return termObject;
				} else {
					throw new InvalidDataException("Term with " + externalRefId
							+ "not present in database.");
				}
			}
			else
			{
				throw new InvalidDataException("The given ID is not corresponding to TERM");
			}
		}

		if (termObject.getId() == null || termObject.getId().trim().equals("")) {
			termObject.setId(IXmlElements.TERM_ID_PERFIX
					+ UUID.randomUUID().getMostSignificantBits());
			termObject.setIdAssigned(true);
		} else {
			if (!termObject.isIdAssigned())
				throw new InvalidDataException(
						"The Id for the term event is already assigned");
		}

		ITermParts termPartsObject = termObject.getPrintedRepresentation();
		ITermParts termParts = getTermPartsObject(termPartsObject,
				referencedList);

		Set<ITermPart> termPartSet = termPartsObject.getTermParts();
		Iterator<ITermPart> termPartIterator = termPartSet.iterator();
		Set<ITermPart> newTermPartSet = new HashSet<ITermPart>();
		while (termPartIterator.hasNext()) {
			ITermPart termPartObject = termPartIterator.next();
			ITermPart termPartNode = getTermPartObject(termPartObject,
					referencedList);
			newTermPartSet.add(termPartNode);
		}
		termParts.setTermParts(newTermPartSet);

		termObject.setPrintedRepresentation(termParts);

		Set<ITerm> referencedTermsList = termObject.getReferencedTerms();
		Iterator<ITerm> referencedTermsIterator = referencedTermsList
				.iterator();
		Set<ITerm> newReferencedTermsList = new HashSet<ITerm>();
		while (referencedTermsIterator.hasNext()) {
			ITerm refTerm = referencedTermsIterator.next();
			ITerm refNode = getTermObject(refTerm, referencedList);
			newReferencedTermsList.add(refNode);
		}
		termObject.setReferencedTerms(newReferencedTermsList);

		return termObject;

	}

	/**
	 * This method stores termParts object into the database.
	 * 
	 * @param termParts
	 *            Object the term object to store in the database.
	 * @return URI of the node in the database.
	 * @throws URISyntaxException
	 */
	public ITermParts getTermPartsObject(ITermParts termPartsObject,
			List<IElement> referencedList) throws InvalidDataException,
			URISyntaxException {
		
		int refFoundFlag = 0;
		if (termPartsObject.getInternal_refId() != null
				&& !termPartsObject.getInternal_refId().trim().equals("")) {
			for (IElement element : referencedList) {
				if (element.getRefId().equals(
						termPartsObject.getInternal_refId())) {
					termPartsObject = (ITermParts) element;
					refFoundFlag = 1;

				}
			}
			if(refFoundFlag == 0)
				throw new InvalidDataException("The referenced term parts is not present");
			
		} else if (termPartsObject.getExternal_refId() != null
				&& !termPartsObject.getExternal_refId().trim().equals("")) {

			String externalRefId = termPartsObject.getExternal_refId();
			if((externalRefId.length() > IXmlElements.TERMPARTS_ID_PREFIX.length()) && externalRefId.substring(0, 3).equals(IXmlElements.TERMPARTS_ID_PREFIX))
			{

				termPartsObject = termPartsRepository.findById(termPartsObject
						.getExternal_refId());

				if (termPartsObject != null) {
					return termPartsObject;
				} else {
					throw new InvalidDataException("Printed Representation with "
							+ externalRefId + "not present in database.");
				}
			}
			else
			{
				throw new InvalidDataException("The given id is not corresponding to TERMPARTS");
			}
		}

		if (termPartsObject.getId() == null
				|| termPartsObject.getId().trim().equals("")) {
			termPartsObject.setId(IXmlElements.TERMPARTS_ID_PREFIX
					+ UUID.randomUUID().getMostSignificantBits());
			termPartsObject.setIdAssigned(true);
		} else {
			if (!termPartsObject.isIdAssigned())
				throw new InvalidDataException(
						"The Id for the termParts event is already assigned");
		}

		return termPartsObject;

	}

	/**
	 * This method stores termPart object into the database.
	 * 
	 * @param termPart
	 *            Object the term object to store in the database.
	 * @return URI of the node in the database.
	 * @throws URISyntaxException
	 */
	public ITermPart getTermPartObject(ITermPart termPartObject,
			List<IElement> referencedList) throws URISyntaxException,
			InvalidDataException {
		
		int refFoundFlag = 0;
		if (termPartObject.getInternal_refId() != null
				&& !termPartObject.getInternal_refId().trim().equals("")) {
			for (IElement element : referencedList) {
				if (element.getRefId().equals(
						termPartObject.getInternal_refId())) {
					termPartObject = (ITermPart) element;
				    refFoundFlag = 1;
				}
			}
			if(refFoundFlag == 0)
				throw new InvalidDataException("The referenced term part is not present");
			
		} else if (termPartObject.getExternal_refId() != null
				&& !termPartObject.getExternal_refId().trim().equals("")) {
			String externalRefId = termPartObject.getExternal_refId();
			if((externalRefId.length()>IXmlElements.TERMPART_ID_PERFIX.length()) && externalRefId.substring(0, 2).equals(IXmlElements.TERMPART_ID_PERFIX))
			{
				termPartObject = termPartRepository.findById(termPartObject
						.getExternal_refId());
				if (termPartObject != null) {
					return termPartObject;
				} else {
					throw new InvalidDataException("TermPart with " + externalRefId
							+ "not present in database.");
				}
			}
			else
			{
				throw new InvalidDataException("The given id is not correspoding to TERMPART");
			}
		}
		if (termPartObject.getId() == null
				|| termPartObject.getId().trim().equals("")) {
			termPartObject.setId(IXmlElements.TERMPART_ID_PERFIX
					+ UUID.randomUUID().getMostSignificantBits());
			termPartObject.setIdAssigned(true);
		} else {
			if (!termPartObject.isIdAssigned())

				throw new InvalidDataException(
						"The Id for the termpart event is already assigned");
		}
		return termPartObject;

	}

}
