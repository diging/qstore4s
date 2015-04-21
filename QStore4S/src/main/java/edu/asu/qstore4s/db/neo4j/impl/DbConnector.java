package edu.asu.qstore4s.db.neo4j.impl;

/**
 *@author Bhargav Desai
 *This class is used to call NEO4j database to get information of relation or appealtion event. 
 * 
 */
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.controller.QStore;
import edu.asu.qstore4s.converter.IXmlElements;
import edu.asu.qstore4s.db.neo4j.IDbConnector;
import edu.asu.qstore4s.domain.elements.impl.Element;
import edu.asu.qstore4s.domain.events.impl.AppellationEvent;
import edu.asu.qstore4s.domain.events.impl.CreationEvent;
import edu.asu.qstore4s.domain.events.impl.RelationEvent;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.repository.AppellationEventRepository;
import edu.asu.qstore4s.repository.RelationEventRepository;
import edu.asu.qstore4s.search.elements.ISearchRelation;
import edu.asu.qstore4s.search.elements.ISearchTerm;
import edu.asu.qstore4s.search.elements.ISearchTermPart;
import edu.asu.qstore4s.search.elements.ISearchTermParts;
import edu.asu.qstore4s.search.events.ISearchAppellationEvent;
import edu.asu.qstore4s.search.events.ISearchCreationEvent;
import edu.asu.qstore4s.search.events.ISearchRelationEvent;
import edu.asu.qstore4s.search.events.impl.SearchAppellationEvent;

/**
 * 
 * @author Bhargav Desai 
 * This class is used to get information of relation or
 * appellation event from the database.
 */

@Service
public class DbConnector implements IDbConnector {
	int counter;
	private static final Logger logger = LoggerFactory.getLogger(QStore.class);

	@Autowired
	private RelationEventRepository relationEventRepository;

	@Autowired
	private AppellationEventRepository appellationEventRepository;

	@Autowired
	private Neo4jTemplate template;

	
	/**
	 * {@inheritDoc}
	 */
	
	@Override
	public Element get(String id) throws InvalidDataException {
		if (id.length() >= IXmlElements.REL_EVENT_ID_PREFIX.length()
				&& id.startsWith(IXmlElements.REL_EVENT_ID_PREFIX)) {

			RelationEvent relationEvent = relationEventRepository.findById(id);

			if (relationEvent == null) {
				throw new InvalidDataException("No relationEvent exists for "
						+ id);
			} else {
				return relationEvent;
			}
		} else if (id.length() >= IXmlElements.APPELLATION_ID_PREFIX.length()
				&& id.startsWith(IXmlElements.APPELLATION_ID_PREFIX)) {
			AppellationEvent appellationEvent = appellationEventRepository
					.findById(id);

			if (appellationEvent == null) {
				throw new InvalidDataException(
						"No appellationEvent exists for " + id);
			} else {
				return appellationEvent;
			}

		}

		else {
			throw new InvalidDataException(
					"No relationEvent or AppelationEvent exists for" + id);

		}

	}

	
	
	/**
	 * {@inheritDoc}
	 */
	
	@Override
	public List<CreationEvent> get(List<String> idList)
			throws InvalidDataException {

		List<CreationEvent> elementList = new ArrayList<CreationEvent>();
		for (String id : idList) {

			Element event = get(id);
			elementList.add((CreationEvent) event);
		}

		return elementList;

	}

	
	/**
	 * {@inheritDoc}
	 */
	
	@Override
	public List<CreationEvent> searchRelationInDb(
			List<Element> creationEventList) throws URISyntaxException,
			InvalidDataException {

		List<CreationEvent> newCreationEventList = new ArrayList<CreationEvent>();
		Iterator<Element> creationEventIterator = creationEventList.iterator();
		while (creationEventIterator.hasNext()) {
			CreationEvent creationEventObject = (CreationEvent) (creationEventIterator
					.next());

			if (creationEventObject instanceof AppellationEvent) {

				AppellationEvent app = appellationEventRepository
						.findById(creationEventObject.getId());

				List<RelationEvent> relationEventList = appellationEventRepository
						.getRelationfromId(app);

				for (RelationEvent relation : relationEventList) {
					newCreationEventList.add(relation);
				}

			}

		}

		return newCreationEventList;

	}

	
	/**
	 * {@inheritDoc}
	 */
	
	@Override
	public List<CreationEvent> searchFromDb(ISearchCreationEvent queryObject)
			throws InvalidDataException {

		List<CreationEvent> resultList = new ArrayList<CreationEvent>();
		StringBuilder matchClause = new StringBuilder();
		StringBuilder whereClause = new StringBuilder();
		StringBuilder match = new StringBuilder();

		if (queryObject instanceof ISearchRelationEvent) {
			match = getRelationEventsClause((ISearchRelationEvent) queryObject,
					whereClause, matchClause);

			logger.info("match---> " + match.toString());

			String query = "start "
					+ queryObject.getName()
					+ " = node(*)  match "
					+ match.toString()
					+ " where "
					+ whereClause.toString().trim()
							.replaceFirst("\\s+\\w+$", "") + "return distinct "
					+ queryObject.getName();
			query = query.replaceAll(",,", ",");
			
			query =query.replaceAll("  ", " ");
			query =query.replaceAll(",,", ",");
			query =query.replaceAll("or or", "or");
			query =query.replaceAll("or and", "and");
			query =query.replaceAll("and or", "and");
			query =query.replaceAll("and and", "and");
			query = query.replace("where and","where");
			query = query.replace("where or","where");



			logger.info(query);

			Result<RelationEvent> result = relationEventRepository.query(
					query, null);

			
			Iterator<RelationEvent> iterator = result.iterator();

			while (iterator.hasNext()) {
				resultList.add(iterator.next());
			}

		} else if (queryObject instanceof ISearchAppellationEvent) {
			match = getAppellationClause((ISearchAppellationEvent) queryObject,
					whereClause, matchClause);

			logger.info("match---> " + matchClause.toString());

			String query = "start "
					+ queryObject.getName()
					+ " = node(*)  match "
					+ match.toString()
					+ " where "
					+ whereClause.toString().trim()
							.replaceFirst("\\s+\\w+$", "") + "return distinct "
					+ queryObject.getName();

			
			query =query.replaceAll("  ", " ");
			
			query =query.replaceAll(",,", ",");
			
			query =query.replaceAll("or or", "or");
			query =query.replaceAll("or and", "and");
			query =query.replaceAll("and or", "and");
			query =query.replaceAll("and and", "and");
			query = query.replace("where and","where");
			query = query.replace("where or","where");


			logger.info(query);

			Result<AppellationEvent> result = appellationEventRepository
					.query(query, null);

			Iterator<AppellationEvent> iterator = result.iterator();

			while (iterator.hasNext()) {
				resultList.add(iterator.next());
			}

		}

		return resultList;

	}

	
	/**
	 * The method builds where and match clause for relation events in neo4j query.
	 * @param queryObject
	 * @param whereClause
	 * @param matchClause
	 * @return
	 */
	private StringBuilder getRelationEventsClause(
			ISearchRelationEvent queryObject, StringBuilder whereClause,
			StringBuilder matchClause) {

		ISearchRelation relationObject = queryObject.getRelation();
		matchClause.append(queryObject.getName() + "- [r"
				+ UUID.randomUUID().toString().replaceAll("-", "") + ":"
				+ IXmlElements.RELATED_TO_RELATION + "] ->"
				+ relationObject.getName());
		StringBuilder matchClauseSubject = new StringBuilder();
		StringBuilder matchClausePredicate = new StringBuilder();
		StringBuilder matchClauseObject = new StringBuilder();

		if (matchClause.toString().endsWith(",")) {
			matchClauseSubject.append(matchClause);
			matchClausePredicate.append(matchClause);

			matchClauseObject.append(matchClause);

		} else {
			matchClauseSubject.append("," + matchClause);
			matchClausePredicate.append("," + matchClause);

			matchClauseObject.append("," + matchClause);

		}

		whereClause
				.append((queryObject.getCreationDate() != null) ? (buildWhereCluse(
						queryObject.getName(), IXmlElements.CREATION_DATE,
						queryObject.getCreationDate().getTime() + "")) : "");

		
	  if(queryObject.getCreationDate()!=null)
		whereClause.append(queryObject.getConnector()!=null ? " "+queryObject.getConnector()+" " : " "+IXmlElements.AND+" ");
		
		
		
		whereClause
				.append((queryObject.getCreationPlace() != null) ? (buildWhereCluse(

						
						queryObject.getName(), IXmlElements.CREATION_PLACE,
						queryObject.getCreationPlace().getSourceURI(), queryObject.getCreationPlace().getSearchType())) : "");

		
		
		
		if(queryObject.getCreationPlace()!=null)
			
		whereClause.append(queryObject.getConnector()!=null ? " "+queryObject.getConnector()+" " : " "+IXmlElements.AND+" ");
		
		
		whereClause
				.append((queryObject.getCreator() != null) ? (buildWhereCluse(
						queryObject.getName(), IXmlElements.CREATOR,
						queryObject.getCreator().getSourceURI(),
						queryObject.getCreator().getSearchType())) : "");

		
		
		if(queryObject.getCreator()!=null)
		whereClause.append(queryObject.getConnector()!=null ? " "+queryObject.getConnector()+" " : " "+IXmlElements.AND+" ");
		
		
		
		whereClause
				.append((queryObject.getInterpretationCreator() != null) ? (buildWhereCluse(
						queryObject.getName(),
						IXmlElements.INTERPRETATION_CREATOR, queryObject
								.getInterpretationCreator().getSourceURI(), queryObject
								.getInterpretationCreator().getSearchType()))
						: "");

		if(queryObject.getInterpretationCreator()!=null)
			
		whereClause.append(queryObject.getConnector()!=null ? " "+queryObject.getConnector()+" " : " "+IXmlElements.AND+" ");
		
		
		
		whereClause
				.append((queryObject.getRelationCreator() != null) ? (buildWhereCluse(
						queryObject.getName(), IXmlElements.RELATION_CREATOR,
						queryObject.getRelationCreator().getSourceURI(), queryObject.getRelationCreator().getSearchType())) : "");

		
		if(queryObject.getRelationCreator()!=null)
		whereClause.append(queryObject.getConnector()!=null ? " "+queryObject.getConnector()+" " : " "+IXmlElements.AND+" ");
		
		
		
		whereClause
				.append((queryObject.getSourceReference() != null) ? (buildWhereCluse(
						queryObject.getName(), IXmlElements.SOURCE_REFERENCE,
						queryObject.getSourceReference().getSourceURI(),queryObject.getSourceReference().getSearchType())) : "");

		
		
		if(queryObject.getSourceReference()!=null)
			
		whereClause.append(" "+IXmlElements.AND+" ");
		
		
		whereClause
				.append((relationObject.getCreationDate() != null) ? (buildWhereCluse(
						relationObject.getName(), IXmlElements.CREATION_DATE,
						relationObject.getCreationDate().getTime() + "")) : "");

		
		if(relationObject.getCreationDate()!=null)
			
		whereClause.append(relationObject.getConnector()!=null ? " "+relationObject.getConnector()+" " : " "+IXmlElements.AND+" ");
		
		
		
		whereClause
				.append((relationObject.getCreationPlace() != null) ? (buildWhereCluse(
						relationObject.getName(), IXmlElements.CREATION_PLACE,
						relationObject.getCreationPlace().getSourceURI(),relationObject.getCreationPlace().getSearchType())) : "");


		if(relationObject.getCreationPlace()!=null)
			
		whereClause.append(relationObject.getConnector()!=null ? " "+relationObject.getConnector()+" " : " "+IXmlElements.AND+" ");
		
		
		whereClause
				.append((relationObject.getCreator() != null) ? (buildWhereCluse(
						relationObject.getName(), IXmlElements.CREATOR,
						relationObject.getCreator().getSourceURI(), relationObject.getCreator().getSearchType())) : "");

		
		
		if(relationObject.getCreator()!=null)
			
		whereClause.append(relationObject.getConnector()!=null ? " "+relationObject.getConnector()+" " : " "+IXmlElements.AND+" ");
		
		
		whereClause
				.append((relationObject.getCreationDate() != null) ? (buildWhereCluse(
						relationObject.getName(), IXmlElements.CREATION_DATE,
						relationObject.getCreationDate().getTime() + "")) : "");

		
		
		if(relationObject.getCreationDate()!=null)
			
		whereClause.append(relationObject.getConnector()!=null ? " "+relationObject.getConnector()+" " : " "+IXmlElements.AND+" ");
		
		
		
		ISearchCreationEvent subjectObject = relationObject.getSubject();

		if (subjectObject != null) {
			matchClauseSubject.append("- [r"
					+ UUID.randomUUID().toString().replaceAll("-", "") + ":"
					+ IXmlElements.RELATED_TO_SUBJECT + "] ->");

			if (subjectObject instanceof ISearchAppellationEvent) {
				matchClauseSubject = getAppellationClause(
						(ISearchAppellationEvent) subjectObject, whereClause,
						matchClauseSubject);

			} else if (subjectObject instanceof ISearchRelationEvent) {
				matchClauseSubject = getRelationEventsClause(
						(ISearchRelationEvent) subjectObject, whereClause,
						matchClauseSubject);

			}

			matchClause.append(matchClauseSubject);

		}

		ISearchCreationEvent predicateObject = relationObject.getPredicate();
		if (predicateObject != null) {

			matchClausePredicate.append("- [r"
					+ UUID.randomUUID().toString().replaceAll("-", "") + ":"
					+ IXmlElements.RELATED_TO_PREDICATE + "] ->");

			if (predicateObject instanceof ISearchAppellationEvent) {
				matchClausePredicate = getAppellationClause(
						(ISearchAppellationEvent) predicateObject, whereClause,
						matchClausePredicate);

			} else if (predicateObject instanceof ISearchRelationEvent) {
				matchClausePredicate = getRelationEventsClause(
						(ISearchRelationEvent) subjectObject, whereClause,
						matchClausePredicate);
			}
			matchClause.append(matchClausePredicate);

		}
		ISearchCreationEvent object = relationObject.getObject();
		if (object != null) {

			matchClauseObject.append("-[r"
					+ UUID.randomUUID().toString().replaceAll("-", "") + ":"
					+ IXmlElements.RELATED_TO_OBJECT + "]->");
			if (object instanceof ISearchAppellationEvent) {
				matchClauseObject = getAppellationClause(
						(ISearchAppellationEvent) object, whereClause,
						matchClauseObject);
			} else if (object instanceof ISearchRelationEvent) {
				matchClauseObject = getRelationEventsClause(
						(ISearchRelationEvent) object, whereClause,
						matchClauseObject);
			}

			matchClause.append(matchClauseObject);

		}

		return matchClause;

	}
	
	
/**
 * The method builds where and match clause for appellation events in neo4j query.
 * @param queryObject
 * @param whereClause
 * @param matchClause
 * @return
 */

	private StringBuilder getAppellationClause(ISearchAppellationEvent queryObject,
			StringBuilder whereClause, StringBuilder matchClause) {
		String matchClauseCopy = new String();
		StringBuilder matchClauseTerm = new StringBuilder();
		ISearchTerm termObject;
		ISearchTermParts termPartsObject;

		matchClauseTerm.append(matchClause);

		matchClauseTerm.append(queryObject.getName() + "-");

		
		
		
		whereClause
				.append((queryObject.getCreationDate() != null) ? (buildWhereCluse(
						queryObject.getName(), IXmlElements.CREATION_DATE,
						queryObject.getCreationDate().getTime() + "")) : "");

		
		
		if(queryObject.getCreationDate() !=null)
			
		whereClause.append(queryObject.getConnector()!=null ? " "+queryObject.getConnector()+" " : " "+IXmlElements.AND+" ");
			
		
		whereClause
				.append((queryObject.getCreator() != null) ? (buildWhereCluse(
						queryObject.getName(), IXmlElements.CREATOR,
						queryObject.getCreator().getSourceURI(),queryObject.getCreator().getSearchType())) : "");

		
		if(queryObject.getCreator() !=null)
	
		whereClause.append(queryObject.getConnector()!=null ? " "+queryObject.getConnector()+" " : " "+IXmlElements.AND+" ");
		
		
		whereClause
				.append((queryObject.getCreationPlace() != null) ? (buildWhereCluse(
						queryObject.getName(), IXmlElements.CREATION_PLACE,
						queryObject.getCreationPlace().getSourceURI(),queryObject.getCreationPlace().getSearchType())) : "");

		if(queryObject.getCreationPlace() !=null)
			
		whereClause.append(queryObject.getConnector()!=null ? " "+queryObject.getConnector()+" " : " "+IXmlElements.AND+" ");
		
		
		whereClause
				.append((queryObject.getInterpretationCreator() != null) ? (buildWhereCluse(
						queryObject.getName(),
						IXmlElements.INTERPRETATION_CREATOR, queryObject
								.getInterpretationCreator().getSourceURI(),queryObject
								.getInterpretationCreator().getSearchType()))
						: "");

		if(queryObject.getInterpretationCreator() !=null)
			
		whereClause.append(queryObject.getConnector()!=null ? " "+queryObject.getConnector()+" " : " "+IXmlElements.AND+" ");
		
		
		
		whereClause
				.append((queryObject.getSourceReference() != null) ? (buildWhereCluse(
						queryObject.getName(), IXmlElements.SOURCE_REFERENCE,
						queryObject.getSourceReference().getSourceURI(),queryObject.getSourceReference().getSearchType())) : "");

		
		
		if(queryObject.getSourceReference() !=null)
			
		whereClause.append(" "+IXmlElements.AND+" ");
		
		
		termObject = ((SearchAppellationEvent) queryObject).getTerm();

		if (termObject != null) {
			matchClauseTerm.append("[r"
					+ UUID.randomUUID().toString().replaceAll("-", "") + ":"
					+ IXmlElements.RELATED_TO_TERM + "] ->"
					+ termObject.getName());

			whereClause
					.append((termObject.getCreationDate() != null) ? (buildWhereCluse(
							termObject.getName(), IXmlElements.CREATION_DATE,
							termObject.getCreationDate().getTime() + "")) : "");

			
			
			if(termObject.getCreationDate() != null)
			whereClause.append(termObject.getConnector()!=null ? " "+termObject.getConnector()+" " : " "+IXmlElements.AND+" ");
			
			
			
			
			whereClause
					.append((termObject.getCreator() != null) ? (buildWhereCluse(
							termObject.getName(), IXmlElements.CREATOR,
							termObject.getCreator().getSourceURI(),termObject.getCreator().getSearchType())) : "");

			
			if(termObject.getCreator() != null)
				
			whereClause.append(termObject.getConnector()!=null ? " "+termObject.getConnector()+" " : " "+IXmlElements.AND+" ");
			
			
			
			whereClause
					.append((termObject.getCreationPlace() != null) ? (buildWhereCluse(
							termObject.getName(), IXmlElements.CREATION_PLACE,
							termObject.getCreationPlace().getSourceURI(),termObject.getCreationPlace().getSearchType())) : "");

			
			if(termObject.getCreationPlace() != null)
				
			whereClause.append(termObject.getConnector()!=null ? " "+termObject.getConnector()+" " : " "+IXmlElements.AND+" ");
			
			
			whereClause
					.append((termObject.getSourceReference() != null) ? (buildWhereCluse(
							termObject.getName(),
							IXmlElements.SOURCE_REFERENCE, termObject
									.getSourceReference().getSourceURI(),termObject
									.getSourceReference().getSearchType())) : "");

			
			if (termObject.getSourceReference() != null)
			whereClause.append(termObject.getConnector()!=null ? " "+termObject.getConnector()+" " : " "+IXmlElements.AND+" ");
			
			
			whereClause.append((termObject.getCertain() != null) ? ("( HAS ("
					+ termObject.getName() + "." + IXmlElements.CERTAIN
					+ ") and " + termObject.getName() + "."
					+ IXmlElements.CERTAIN + "=" + termObject.getCreationDate()
					.getTime()) + ") " + IXmlElements.AND + " " : "");

			
			if(termObject.getCertain() != null)

			whereClause.append(termObject.getConnector()!=null ? " "+termObject.getConnector()+" " : " "+IXmlElements.AND+" ");
			
			whereClause
					.append((termObject.getInterpretation() != null) ? (buildWhereCluse(
							termObject.getName(), IXmlElements.INTERPRETATION,
							termObject.getInterpretation().getSourceURI(),termObject.getInterpretation().getSearchType()))
							: "");

			if(termObject.getInterpretation() != null)

			whereClause.append(termObject.getConnector()!=null ? " "+termObject.getConnector()+" " : " "+IXmlElements.AND+" ");
			
			
			whereClause
					.append((termObject.getNormalizedRepresentation() != null) ? (buildWhereCluse(
							termObject.getName(),
							IXmlElements.NORMALIZED_REPRESENTATION, termObject
									.getNormalizedRepresentation()
									.getSourceURI(),termObject
									.getNormalizedRepresentation().getSearchType()
									+ "")) : "");

			
			
			if(termObject.getNormalizedRepresentation() !=null)
				
			whereClause.append(" "+IXmlElements.AND+" ");
			
			
			termPartsObject = termObject.getPrintedRepresentation();
			if (termPartsObject != null) {
				matchClauseTerm.append(" - [r"
						+ UUID.randomUUID().toString().replaceAll("-", "")
						+ ":" + IXmlElements.RELATED_TO_TERMPARTS + "] ->"
						+ termPartsObject.getName());

				whereClause
						.append((termPartsObject.getCreationDate() != null) ? (buildWhereCluse(
								termPartsObject.getName(),
								IXmlElements.CREATION_DATE, termPartsObject
										.getCreationDate().getTime() + ""))
								: "");

				
				
				if(termPartsObject.getCreationDate() != null)

			
				whereClause.append(termPartsObject.getConnector()!=null ? " "+termPartsObject.getConnector()+" " : " "+IXmlElements.AND+" ");

				
				
				whereClause
						.append((termPartsObject.getCreationPlace() != null) ? (buildWhereCluse(
								termPartsObject.getName(),
								IXmlElements.CREATION_PLACE, termPartsObject
										.getCreationPlace().getSourceURI(),termPartsObject.getCreationPlace().getSearchType()))
								: "");

				
				
				if(termPartsObject.getCreationPlace() != null)

				whereClause.append(termPartsObject.getConnector()!=null ? " "+termPartsObject.getConnector()+" " : " "+IXmlElements.AND+" ");
	
				
				whereClause
						.append((termPartsObject.getCreator() != null) ? (buildWhereCluse(
								termPartsObject.getName(),
								IXmlElements.CREATOR, termPartsObject
										.getCreator().getSourceURI(), termPartsObject
										.getCreator().getSearchType())) : "");

				
				
				if(termPartsObject.getCreator() !=null)
					
				whereClause.append(" "+IXmlElements.AND+" ");
				
				
				Set<ISearchTermPart> termPartList = termPartsObject
						.getSearchTermParts();
				if (termPartList != null) {
					Iterator<ISearchTermPart> termPartIterator = termPartList
							.iterator();
					while (termPartIterator.hasNext()) {
						ISearchTermPart termPartObject = termPartIterator
								.next();
						matchClauseCopy = "- [r"
								+ UUID.randomUUID().toString()
										.replaceAll("-", "") + ":"
								+ IXmlElements.RELATED_TO_TERMPART + " ] ->"
								+ termPartObject.getName();

						whereClause
								.append((termPartObject.getCreationDate() != null) ? (buildWhereCluse(
										termPartObject.getName(),
										IXmlElements.CREATION_DATE,
										termPartObject.getCreationDate()
												.getTime() + "")) : "");

					
						if(termPartObject.getCreationDate() != null)

						whereClause.append(termPartObject.getConnector()!=null ? " "+termPartObject.getConnector()+" " : " "+IXmlElements.AND+" ");

						
						
						whereClause
								.append((termPartObject.getCreationPlace() != null) ? (buildWhereCluse(
										termPartObject.getName(),
										IXmlElements.CREATION_PLACE,
										termPartObject.getCreationPlace()
												.getSourceURI(),termPartObject.getCreationPlace().getSearchType())) : "");

						
						if(termPartObject.getCreationPlace() != null)

						whereClause.append(termPartObject.getConnector()!=null ? " "+termPartObject.getConnector()+" " : " "+IXmlElements.AND+" ");
						
						
						whereClause
								.append((termPartObject.getCreator() != null) ? (buildWhereCluse(
										termPartObject.getName(),
										IXmlElements.CREATOR, termPartObject
												.getCreator().getSourceURI(), termPartObject
												.getCreator().getSearchType()))
										: "");
						
					
						if(termPartObject.getCreator() != null)

						whereClause.append(termPartObject.getConnector()!=null ? " "+termPartObject.getConnector()+" " : " "+IXmlElements.AND+" ");
						
						
						whereClause
								.append((termPartObject.getNormalization() != null) ? (buildWhereCluse(
										termPartObject.getName(),
										IXmlElements.NORMALIZATION,
										termPartObject.getNormalization()
												.getSourceURI(),termPartObject.getNormalization().getSearchType())) : "");
						
						
						
						if(termPartObject.getNormalization() != null)

						whereClause.append(termPartObject.getConnector()!=null ? " "+termPartObject.getConnector()+" " : " "+IXmlElements.AND+" ");
						
						
						whereClause
								.append((termPartObject.getSearchExpression() != null) ? (buildWhereCluse(
										termPartObject.getName(),
										IXmlElements.EXPRESSION, termPartObject
												.getSearchExpression()
												.getExpression(),termPartObject
												.getSearchExpression().getSearchType())) : "");
						
						if(termPartObject.getSearchExpression() != null)

						whereClause.append(termPartObject.getConnector()!=null ? " "+termPartObject.getConnector()+" " : " "+IXmlElements.AND+" ");
						
						
						
						whereClause
								.append((termPartObject.getSearchFormat() != null) ? (buildWhereCluse(
										termPartObject.getName(),
										IXmlElements.FORMAT, termPartObject
												.getSearchFormat().getFormat(), termPartObject
												.getSearchFormat().getSearchType()))
										: "");
						
						
						
						if(termPartObject.getSearchFormat() != null)

						whereClause.append(termPartObject.getConnector()!=null ? " "+termPartObject.getConnector()+" " : " "+IXmlElements.AND+" ");
						
						
						whereClause
								.append((termPartObject
										.getSearchFormattedPointer() != null) ? (buildWhereCluse(
										termPartObject.getName(),
										IXmlElements.FORMATTED_POINTER,
										termPartObject
												.getSearchFormattedPointer()
												.getFormattedPointer(),termPartObject
												.getSearchFormattedPointer().getSearchType())) : "");
						
						
						if(termPartObject.getSearchFormattedPointer() != null)

						whereClause.append(termPartObject.getConnector()!=null ? " "+termPartObject.getConnector()+" " : " "+IXmlElements.AND+" ");
						
						
						whereClause
								.append((termPartObject.getSourceReference() != null) ? (buildWhereCluse(
										termPartObject.getName(),
										IXmlElements.SOURCE_REFERENCE,
										termPartObject.getSourceReference()
												.getSourceURI(),termPartObject.getSourceReference().getSearchType())) : "");
						// temp = matchClauseTerm.toString() + matchClauseCopy;
						matchClauseTerm.append(matchClauseCopy);
					}
				} else {
					matchClauseCopy = "- [r"
							+ UUID.randomUUID().toString().replaceAll("-", "")
							+ ":" + IXmlElements.RELATED_TO_TERMPART
							+ "] -> termPart";
				}

			} else {
				matchClauseTerm.append("- [r"
						+ UUID.randomUUID().toString().replaceAll("-", "")
						+ ":" + IXmlElements.RELATED_TO_TERMPARTS
						+ "termParts - [r"
						+ UUID.randomUUID().toString().replaceAll("-", "")
						+ ":" + IXmlElements.RELATED_TO_TERMPART
						+ " ] -> termPart");
			}
		} else {
			matchClauseTerm.append("- [r "
					+ UUID.randomUUID().toString().replaceAll("-", "") + ":"
					+ IXmlElements.RELATED_TO_TERM + "term -[r"
					+ UUID.randomUUID().toString().replaceAll("-", "") + ":"
					+ IXmlElements.RELATED_TO_TERMPARTS + "] -> termParts -[r"
					+ UUID.randomUUID().toString().replaceAll("-", "") + ":"
					+ IXmlElements.RELATED_TO_TERMPART + "] -> termPart");
		}

		return matchClauseTerm;

	}

	
	
	/**
	 * The method build where clause for given object and property values and also consider weather property value is regualr expression or not.
	 * @param name
	 * @param property
	 * @param value
	 * @param searchType
	 * @return
	 */
	private String buildWhereCluse(String name, String property, String value,String searchType) {
		
		if(searchType!=null && searchType.equals("regex") )
			return "( HAS (" + name + "." + property + ") and " + name + "."
			+ property + "=~'" + value + "') " ;
		
		
		return "( HAS (" + name + "." + property + ") and " + name + "."
				+ property + "='" + value + "') ";

	}
	
	
	/**
	 *The method build where clause for given object and property values. 
	 * @param name
	 * @param property
	 * @param value
	 * @return
	 */
	
	private String buildWhereCluse(String name, String property, String value) {
		
		
		
		return "( HAS (" + name + "." + property + ") and " + name + "."
				+ property + "='" + value + "') ";

	}

}
