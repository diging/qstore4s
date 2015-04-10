package edu.asu.qstore4s.converter.impl;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.converter.ISearchXmlParser;
import edu.asu.qstore4s.converter.IXmlElements;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;
import edu.asu.qstore4s.search.elements.ISearchRelation;
import edu.asu.qstore4s.search.elements.ISearchTerm;
import edu.asu.qstore4s.search.elements.ISearchTermPart;
import edu.asu.qstore4s.search.elements.ISearchTermParts;
import edu.asu.qstore4s.search.elements.factory.ISearchActorFactory;
import edu.asu.qstore4s.search.elements.factory.ISearchConceptFactory;
import edu.asu.qstore4s.search.elements.factory.ISearchExpressionFactory;
import edu.asu.qstore4s.search.elements.factory.ISearchFormatFactory;
import edu.asu.qstore4s.search.elements.factory.ISearchFormattedPointerFactory;
import edu.asu.qstore4s.search.elements.factory.ISearchPlaceFactory;
import edu.asu.qstore4s.search.elements.factory.ISearchRelationFactory;
import edu.asu.qstore4s.search.elements.factory.ISearchSourceReferenceFactory;
import edu.asu.qstore4s.search.elements.factory.ISearchTermFactory;
import edu.asu.qstore4s.search.elements.factory.ISearchTermPartFactory;
import edu.asu.qstore4s.search.elements.factory.ISearchTermPartsFactory;
import edu.asu.qstore4s.search.elements.factory.ISearchVocabularyEntryFactory;
import edu.asu.qstore4s.search.events.ISearchAppellationEvent;
import edu.asu.qstore4s.search.events.ISearchCreationEvent;
import edu.asu.qstore4s.search.events.ISearchRelationEvent;
import edu.asu.qstore4s.search.events.factory.ISearchAppellationEventFactory;
import edu.asu.qstore4s.search.events.factory.ISearchRelationEventFactory;

/**
 * 
 * @author Bhargav Desai & Veena Borannagowda
 *
 */

@Service
public class SearchXmlParser  implements ISearchXmlParser {

	@Autowired
	private ISearchRelationFactory relationFactory;
	@Autowired
	private ISearchActorFactory actorFactory;
	@Autowired
	private ISearchConceptFactory conceptFactory;
	@Autowired
	private ISearchPlaceFactory placeFactory;
	@Autowired
	private ISearchSourceReferenceFactory sourceReferenceFactory;
	@Autowired
	private ISearchTermFactory termFactory;
	@Autowired
	private ISearchTermPartFactory termPartFactory;
	@Autowired
	private ISearchTermPartsFactory termPartsFactory;
	@Autowired
	private ISearchVocabularyEntryFactory vocabularyEntryFactory;
	@Autowired
	private ISearchRelationEventFactory relationEventFactory;
	@Autowired
	private ISearchAppellationEventFactory appellationEventFactory;
	@Autowired
	private ISearchExpressionFactory searchExpressionFactory;
	@Autowired
	private ISearchFormatFactory formatFactory;
	@Autowired
	private ISearchFormattedPointerFactory formattedPointerFactory;

	/**
	 * This method parses the XML file and creates the Objects for storing in
	 * the Database.
	 * 
	 * @param xml
	 *            Input file given by the user in form of string.
	 * @throws InvalidDataException
	 * @returns list of creation events present in the input string.
	 */

	@Override
	public ISearchCreationEvent parseXML(String xml) throws ParserException,
			IOException, URISyntaxException, ParseException,
			InvalidDataException {

		SAXBuilder XMLbuilder = new SAXBuilder();
		Document XMLDocument;

		try {
			String formattedXML = Format.compact(xml);
			XMLDocument = (Document) XMLbuilder.build((new StringReader(
					formattedXML)));

		} catch (JDOMException e) {
			throw new ParserException(e.toString());
		}

		Element rootElement = XMLDocument.getRootElement();
		Namespace nameSpace = rootElement.getNamespace();
		List<Element> Children = rootElement.getChildren();
		Iterator<Element> childrenIterator = Children.iterator();

		Element childNode = childrenIterator.next();
		if (childNode.getName().equals(IXmlElements.APPELLATION_EVENT)) {
			ISearchAppellationEvent rootNode = getAppellationEvent(childNode,
					nameSpace);

			return rootNode;

		}

		else if (childNode.getName().equals(IXmlElements.RELATION_EVENT))

		{

			ISearchRelationEvent rootNode = getRelationEvent(childNode,
					nameSpace);
			return rootNode;

		} else {
			throw new InvalidDataException();
		}

	}

	/**
	 * This method creates objects for the relational event present in the input
	 * string.
	 * 
	 * @param relationEvent
	 *            Element object from the input string which is a relational
	 *            event.
	 * @return RelationEvent object.
	 */

	public ISearchRelationEvent getRelationEvent(Element relationEvent,
			Namespace nameSpace) throws ParserException, IOException,
			URISyntaxException, ParseException {

		DateFormat formatter;
		formatter = new SimpleDateFormat(IXmlElements.DATE_FORMAT);

		ISearchRelationEvent relationEventObject = relationEventFactory
				.createRelationEvent();


		String name = (IXmlElements.RELATION_EVENT
				+ UUID.randomUUID().getLeastSignificantBits()).replaceAll("-", "");
		
		
		relationEventObject.setName(name);

		relationEventObject.setId(checkForSpaces(relationEvent.getChildText(
				IXmlElements.ID, nameSpace)));

		relationEventObject.setConnector(checkForSpaces(relationEvent
				.getChildText(IXmlElements.CONNECTOR, nameSpace)));

		{
			Element child = relationEvent.getChild(IXmlElements.CREATOR,
					nameSpace);

			if (child != null) {

				relationEventObject
						.setCreator(actorFactory
								.createSearchActor(checkForSpaces(relationEvent
										.getChildText(IXmlElements.CREATOR,
												nameSpace))));

				relationEventObject.getCreator().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		if (checkForSpaces(relationEvent.getChildText(
				IXmlElements.CREATION_DATE, nameSpace)) != null) {

			relationEventObject.setCreationDate((Date) formatter
					.parse(checkForSpaces(relationEvent.getChildText(
							IXmlElements.CREATION_DATE, nameSpace))));
		}

		{
			Element child = relationEvent.getChild(IXmlElements.CREATION_PLACE,
					nameSpace);

			if (child != null) {

				relationEventObject.setCreationPlace(placeFactory
						.createSearchPlace(checkForSpaces(relationEvent
								.getChildText(IXmlElements.CREATION_PLACE,
										nameSpace))));

				relationEventObject.getCreationPlace().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		{
			Element child = relationEvent.getChild(
					IXmlElements.RELATION_CREATOR, nameSpace);

			if (child != null) {

				relationEventObject.setRelationCreator(actorFactory
						.createSearchActor(checkForSpaces(relationEvent
								.getChildText(IXmlElements.RELATION_CREATOR,
										nameSpace))));

				relationEventObject.getRelationCreator().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		{
			Element child = relationEvent.getChild(
					IXmlElements.SOURCE_REFERENCE, nameSpace);

			if (child != null) {

				relationEventObject
						.setSourceReference(sourceReferenceFactory
								.createSearchSourceReference(checkForSpaces(relationEvent
										.getChildText(
												IXmlElements.SOURCE_REFERENCE,
												nameSpace))));

				relationEventObject.getSourceReference().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		{
			Element child = relationEvent.getChild(
					IXmlElements.INTERPRETATION_CREATOR, nameSpace);

			if (child != null) {

				relationEventObject.setInterpretationCreator(actorFactory
						.createSearchActor(checkForSpaces(relationEvent
								.getChildText(
										IXmlElements.INTERPRETATION_CREATOR,
										nameSpace))));

				relationEventObject.getInterpretationCreator().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		Element relationChild = relationEvent.getChild(IXmlElements.RELATION,
				nameSpace);

		ISearchRelation relationObject = relationFactory.createSearchRelation();
		
		

		String nameRelation = (IXmlElements.RELATION
				+ UUID.randomUUID().getLeastSignificantBits()).replaceAll("-", "");
		

		relationObject.setName(nameRelation);

		relationObject.setConnector(checkForSpaces(relationChild.getChildText(
				IXmlElements.CONNECTOR, nameSpace)));

		relationObject.setId(checkForSpaces(relationChild.getChildText(
				IXmlElements.ID, nameSpace)));

		{
			Element child = relationChild.getChild(IXmlElements.CREATOR,
					nameSpace);

			if (child != null) {

				relationObject
						.setCreator(actorFactory
								.createSearchActor(checkForSpaces(relationChild
										.getChildText(IXmlElements.CREATOR,
												nameSpace))));

				relationObject.getCreator().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		if (checkForSpaces(relationChild.getChildText(
				IXmlElements.CREATION_DATE, nameSpace)) != null) {

			relationObject.setCreationDate((Date) formatter
					.parse(checkForSpaces(relationChild.getChildText(
							IXmlElements.CREATION_DATE, nameSpace))));
		}

		{
			Element child = relationChild.getChild(IXmlElements.CREATION_PLACE,
					nameSpace);

			if (child != null) {
				relationObject.setCreationPlace(placeFactory
						.createSearchPlace(checkForSpaces(relationChild
								.getChildText(IXmlElements.CREATION_PLACE,
										nameSpace))));

				relationObject.getCreationPlace().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		{
			Element child = relationChild.getChild(
					IXmlElements.SOURCE_REFERENCE, nameSpace);

			if (child != null) {

				relationObject
						.setSourceReference(sourceReferenceFactory
								.createSearchSourceReference(checkForSpaces(relationChild
										.getChildText(
												IXmlElements.SOURCE_REFERENCE,
												nameSpace))));

				relationObject.getSourceReference().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		Element subjectChild = relationChild.getChild(IXmlElements.SUBJECT,
				nameSpace);

		if (subjectChild != null) {

			{
				List<Element> subject = subjectChild.getChildren();
				Iterator<Element> subjectIterator = subject.iterator();
				Element appellationRelation = subjectIterator.next();

				if (appellationRelation.getName().equals(
						IXmlElements.APPELLATION_EVENT)) {
					ISearchAppellationEvent appellationSubEventObject = getAppellationEvent(
							appellationRelation, nameSpace);
					relationObject.setSubject(appellationSubEventObject);

				} else if (appellationRelation.getName().equals(
						IXmlElements.RELATION_EVENT)) {
					ISearchRelationEvent childRelationEventObject = relationEventFactory
							.createRelationEvent();
					childRelationEventObject = getRelationEvent(
							appellationRelation, nameSpace);
					relationObject.setSubject(childRelationEventObject);

				}

			}

		}
		
		
		Element predicateChild = relationChild.getChild(IXmlElements.PREDICATE,
				nameSpace);

		if (predicateChild != null) {

			{
				Element appellationRelation = predicateChild.getChild(
						IXmlElements.APPELLATION_EVENT, nameSpace);
				ISearchAppellationEvent appellationPredEventObject = getAppellationEvent(
						appellationRelation, nameSpace);
				relationObject.setPredicate(appellationPredEventObject);
			}

		}

		Element objectChild = relationChild.getChild(IXmlElements.OBJECT,
				nameSpace);

		if (objectChild != null) {

			{
				List<Element> object = objectChild.getChildren();
				Iterator<Element> objectIterator = object.iterator();
				Element appellationRelation = objectIterator.next();

				if (checkForSpaces(appellationRelation.getName()).equals(
						IXmlElements.APPELLATION_EVENT)) {
					ISearchAppellationEvent appellationObjEventObject = getAppellationEvent(
							appellationRelation, nameSpace);
					relationObject.setObject(appellationObjEventObject);
				} else if (checkForSpaces(objectChild.getName()).equals(
						IXmlElements.RELATION)) {
					ISearchRelationEvent childRelationEventObject = relationEventFactory
							.createRelationEvent();
					childRelationEventObject = getRelationEvent(
							appellationRelation, nameSpace);
					relationObject.setObject(childRelationEventObject);
				}

			}
		}

		relationEventObject.setRelation(relationObject);

		return relationEventObject;
	}

	/**
	 * This method creates objects for the appellation event present in the
	 * input string.
	 * 
	 * @param appellationEvent
	 *            Element object from the input string which is an appellation
	 *            event.
	 * @return AppellationEvent object.
	 */

	public ISearchAppellationEvent getAppellationEvent(
			Element appellationEvent, Namespace nameSpace)
			throws ParserException, IOException, URISyntaxException,
			ParseException {

		DateFormat formatter;
		formatter = new SimpleDateFormat(IXmlElements.DATE_FORMAT);
		ISearchAppellationEvent appellationEventObject = appellationEventFactory
				.createAppellationEvent();

		appellationEventObject.setId(checkForSpaces(appellationEvent
				.getChildText(IXmlElements.ID, nameSpace)));

		
		String name = (IXmlElements.APPELLATION_EVENT
				+ UUID.randomUUID().getLeastSignificantBits()).replaceAll("-", "");
		
		appellationEventObject.setName(name);
		


		{
			Element child = appellationEvent.getChild(IXmlElements.CREATOR,
					nameSpace);

			if (child != null) {

				appellationEventObject
						.setCreator(actorFactory
								.createSearchActor(checkForSpaces(appellationEvent
										.getChildText(IXmlElements.CREATOR,
												nameSpace))));

				appellationEventObject.getCreator().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		if (checkForSpaces(appellationEvent.getChildText(
				IXmlElements.CREATION_DATE, nameSpace)) != null) {

			appellationEventObject.setCreationDate((Date) formatter
					.parse(checkForSpaces(appellationEvent.getChildText(
							IXmlElements.CREATION_DATE, nameSpace))));
		}

		appellationEventObject.setConnector(checkForSpaces(appellationEvent
				.getChildText(IXmlElements.CONNECTOR, nameSpace)));

		{
			Element child = appellationEvent.getChild(
					IXmlElements.CREATION_PLACE, nameSpace);

			if (child != null) {

				appellationEventObject.setCreationPlace(placeFactory
						.createSearchPlace(checkForSpaces(appellationEvent
								.getChildText(IXmlElements.CREATION_PLACE,
										nameSpace))));

				appellationEventObject.getCreationPlace().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		{
			Element child = appellationEvent.getChild(
					IXmlElements.INTERPRETATION_CREATOR, nameSpace);

			if (child != null) {

				appellationEventObject.setInterpretationCreator(actorFactory
						.createSearchActor(checkForSpaces(appellationEvent
								.getChildText(
										IXmlElements.INTERPRETATION_CREATOR,
										nameSpace))));

				appellationEventObject
						.getInterpretationCreator()
						.setSearchType(
								child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		{
			Element child = appellationEvent.getChild(
					IXmlElements.SOURCE_REFERENCE, nameSpace);

			if (child != null) {

				appellationEventObject
						.setSourceReference(sourceReferenceFactory
								.createSearchSourceReference(checkForSpaces(appellationEvent
										.getChildText(
												IXmlElements.SOURCE_REFERENCE,
												nameSpace))));

				appellationEventObject.getSourceReference().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		Element term = (Element) appellationEvent.getChild(IXmlElements.TERM,
				nameSpace);

		if (term != null) {
			ISearchTerm termObject = getTermObject(term, nameSpace);

			appellationEventObject.setTerm(termObject);
		}

		return appellationEventObject;
	}

	/**
	 * This method creates object for the Term present in the appellation Event.
	 * 
	 * @param term
	 *            Element Term object from the input string which is in an
	 *            appellation event.
	 * @param nameSpace
	 *            NameSpace of the input XML.
	 * @param referencedObjectList
	 *            List containing the referenced Objects
	 * @return Term object.
	 */
	public ISearchTerm getTermObject(Element term, Namespace nameSpace)
			throws ParseException {
		DateFormat formatter;
		formatter = new SimpleDateFormat(IXmlElements.DATE_FORMAT);
		ISearchTerm termObject = termFactory.createSearchTerm();

		termObject.setConnector(checkForSpaces(term.getChildText(
				IXmlElements.CONNECTOR, nameSpace)));


		String name = (IXmlElements.TERM
				+ UUID.randomUUID().getLeastSignificantBits()).replaceAll("-", "");
		
		
		termObject.setName(name);

		termObject.setId(checkForSpaces(term.getChildText(IXmlElements.ID,
				nameSpace)));

		{
			Element child = term.getChild(IXmlElements.CREATOR, nameSpace);

			if (child != null) {

				termObject.setCreator(actorFactory
						.createSearchActor(checkForSpaces(term.getChildText(
								IXmlElements.CREATOR, nameSpace))));

				termObject.getCreator().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		{
			Element child = term.getChild(IXmlElements.CREATION_PLACE,
					nameSpace);

			if (child != null) {

				termObject.setCreationPlace(placeFactory
						.createSearchPlace(checkForSpaces(term.getChildText(
								IXmlElements.CREATION_PLACE, nameSpace))));

				termObject.getCreationPlace().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		if (checkForSpaces(term.getChildText(IXmlElements.CREATION_DATE,
				nameSpace)) != null) {

			termObject.setCreationDate((Date) formatter
					.parse(checkForSpaces(term.getChildText(
							IXmlElements.CREATION_DATE, nameSpace))));
		}
		{
			Element child = term.getChild(IXmlElements.INTERPRETATION,
					nameSpace);

			if (child != null) {

				termObject.setInterpretation(conceptFactory
						.createSearchConcept(checkForSpaces(term.getChildText(
								IXmlElements.INTERPRETATION, nameSpace))));

				termObject.getInterpretation().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		{
			Element child = term.getChild(IXmlElements.SOURCE_REFERENCE,
					nameSpace);

			if (child != null) {

				termObject.setSourceReference(sourceReferenceFactory
						.createSearchSourceReference(checkForSpaces(term
								.getChildText(IXmlElements.SOURCE_REFERENCE,
										nameSpace))));

				termObject.getSourceReference().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		{
			Element child = term.getChild(
					IXmlElements.NORMALIZED_REPRESENTATION, nameSpace);

			if (child != null) {

				termObject.setCertain(new Boolean(checkForSpaces(term
						.getChildText(IXmlElements.CERTAIN, nameSpace))));

				termObject.setNormalizedRepresentation(vocabularyEntryFactory
						.createSearchVocabularyEntry(checkForSpaces(term
								.getChildText(
										IXmlElements.NORMALIZED_REPRESENTATION,
										nameSpace))));

				termObject.getNormalizedRepresentation().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
				
				
				
			}

		}

		Element printedRepresentation = (Element) term.getChild(
				IXmlElements.PRINTED_REPRESENTATION, nameSpace);

		if (printedRepresentation != null) {

			ISearchTermParts termPartsObject = getTermPartsObject(
					printedRepresentation, nameSpace);

			termObject.setPrintedRepresentation(termPartsObject);
		}
		return termObject;
	}

	/**
	 * This method creates object for the TermParts present in the Term Element.
	 * 
	 * @param printedRepresentation
	 *            TermParts object from the input string which is in an Term
	 *            element.
	 * @param nameSpace
	 *            NameSpace of the input XML.
	 * @param referencedObjectList
	 *            List containing the referenced Objects
	 * @return TermParts object.
	 */
	public ISearchTermParts getTermPartsObject(Element printedRepresentation,
			Namespace nameSpace) throws ParseException {
		DateFormat formatter;
		formatter = new SimpleDateFormat(IXmlElements.DATE_FORMAT);
		ISearchTermParts termPartsObject = termPartsFactory
				.createSearchTermParts();

		termPartsObject.setConnector(checkForSpaces(printedRepresentation
				.getChildText(IXmlElements.CONNECTOR, nameSpace)));
		

		String name = (IXmlElements.PRINTED_REPRESENTATION
				+ UUID.randomUUID().getLeastSignificantBits()).replaceAll("-", "");
		

		termPartsObject.setName(name);

		termPartsObject.setId(checkForSpaces(printedRepresentation
				.getChildText(IXmlElements.ID, nameSpace)));
		{
			Element child = printedRepresentation.getChild(
					IXmlElements.CREATOR, nameSpace);

			if (child != null) {

				termPartsObject
						.setCreator(actorFactory
								.createSearchActor(checkForSpaces(printedRepresentation
										.getChildText(IXmlElements.CREATOR,
												nameSpace))));

				termPartsObject.getCreator().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		{
			Element child = printedRepresentation.getChild(
					IXmlElements.CREATION_PLACE, nameSpace);

			if (child != null) {

				termPartsObject.setCreationPlace(placeFactory
						.createSearchPlace(checkForSpaces(printedRepresentation
								.getChildText(IXmlElements.CREATION_PLACE,
										nameSpace))));

				termPartsObject.getCreationPlace().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		if (checkForSpaces(printedRepresentation.getChildText(
				IXmlElements.CREATION_DATE, nameSpace)) != null) {

			termPartsObject.setCreationDate((Date) formatter
					.parse(checkForSpaces(printedRepresentation.getChildText(
							IXmlElements.CREATION_DATE, nameSpace))));
		}

		{
			Element child = printedRepresentation.getChild(
					IXmlElements.SOURCE_REFERENCE, nameSpace);

			if (child != null) {

				termPartsObject
						.setReferencedSource(sourceReferenceFactory
								.createSearchSourceReference(checkForSpaces(printedRepresentation
										.getChildText(
												IXmlElements.SOURCE_REFERENCE,
												nameSpace))));

				termPartsObject.getReferencedSource().setSearchType(
						child.getAttributeValue(IXmlElements.SEARCH_TYPE));
			}

		}

		List<Element> TermPartNodesArrayList = printedRepresentation
				.getChildren(IXmlElements.TERM_PART, nameSpace);
		Set<Element> TermPartNodes = new HashSet<Element>(
				TermPartNodesArrayList);
		Set<ISearchTermPart> termPartList = getTermPartObject(TermPartNodes,
				nameSpace);

		termPartsObject.setSearchTermParts(termPartList);

		return termPartsObject;
	}

	/**
	 * This method creates list of TermPart objects present in the TermParts
	 * Element.
	 * 
	 * @param TermPartNodes
	 *            List of TermPart elements from the input string which is in an
	 *            TermParts element.
	 * @param nameSpace
	 *            NameSpace of the input XML.
	 * @param referencedObjectList
	 *            List containing the referenced Objects
	 * @return List of TermPart object.
	 */
	public Set<ISearchTermPart> getTermPartObject(Set<Element> TermPartNodes,
			Namespace nameSpace) throws ParseException {
		DateFormat formatter;
		formatter = new SimpleDateFormat(IXmlElements.DATE_FORMAT);

		Iterator<Element> TermPartIterator = TermPartNodes.iterator();
		Set<ISearchTermPart> termPartList = new HashSet<ISearchTermPart>();

		while (TermPartIterator.hasNext()) {
			ISearchTermPart termPartObject = termPartFactory
					.createSearchTermPart();
			Element currentElement = (Element) TermPartIterator.next();

			termPartObject.setConnector(checkForSpaces(currentElement
					.getChildText(IXmlElements.CONNECTOR, nameSpace)));


			String name = (IXmlElements.TERM_PART
					+ UUID.randomUUID().getLeastSignificantBits()).replaceAll("-", "");
			
			
			termPartObject.setName(name);

			termPartObject.setId(checkForSpaces(currentElement.getChildText(
					IXmlElements.ID, nameSpace)));

			{
				Element child = currentElement.getChild(IXmlElements.CREATOR,
						nameSpace);

				if (child != null) {

					termPartObject.setCreator(actorFactory
							.createSearchActor(checkForSpaces(currentElement
									.getChildText(IXmlElements.CREATOR,
											nameSpace))));

					termPartObject.getCreator().setSearchType(
							child.getAttributeValue(IXmlElements.SEARCH_TYPE));
				}

			}

			{
				Element child = currentElement.getChild(
						IXmlElements.CREATION_PLACE, nameSpace);

				if (child != null) {

					termPartObject.setCreationPlace(placeFactory
							.createSearchPlace(checkForSpaces(currentElement
									.getChildText(IXmlElements.CREATION_PLACE,
											nameSpace))));

					termPartObject.getCreationPlace().setSearchType(
							child.getAttributeValue(IXmlElements.SEARCH_TYPE));
				}

			}

			if (checkForSpaces(currentElement.getChildText(
					IXmlElements.CREATION_DATE, nameSpace)) != null) {

				termPartObject.setCreationDate((Date) formatter
						.parse(checkForSpaces(currentElement.getChildText(
								IXmlElements.CREATION_DATE, nameSpace))));

			}
			
			
			{
				Element child = currentElement.getChild(
						IXmlElements.SOURCE_REFERENCE, nameSpace);

				if (child != null) {

					
					
					termPartObject.setSourceReference(sourceReferenceFactory
							.createSearchSourceReference(checkForSpaces(currentElement
									.getChildText(IXmlElements.SOURCE_REFERENCE,
											nameSpace))));


					
					termPartObject.getSourceReference().setSearchType(
							child.getAttributeValue(IXmlElements.SEARCH_TYPE));
				}

			}
			{
				Element child = currentElement.getChild(
						IXmlElements.EXPRESSION, nameSpace);

				if (child != null) {

					termPartObject
							.setSearchExpression(searchExpressionFactory
									.createSearchExpression(checkForSpaces(currentElement
											.getChildText(
													IXmlElements.EXPRESSION,
													nameSpace))));

					termPartObject.getSearchExpression().setSearchType(
							child.getAttributeValue(IXmlElements.SEARCH_TYPE));
				}

			}

			{
				Element child = currentElement.getChild(
						IXmlElements.NORMALIZATION, nameSpace);

				if (child != null) {

					termPartObject
							.setNormalization(vocabularyEntryFactory
									.createSearchVocabularyEntry(checkForSpaces(currentElement
											.getChildText(
													IXmlElements.NORMALIZATION,
													nameSpace))));

					termPartObject.getNormalization().setSearchType(
							child.getAttributeValue(IXmlElements.SEARCH_TYPE));
				}

			}

			if (checkForSpaces(currentElement.getChildText(
					IXmlElements.POSITION, nameSpace)) != null) {

				termPartObject.setPosition(Integer
						.parseInt(checkForSpaces(currentElement.getChildText(
								IXmlElements.POSITION, nameSpace))));
			}
			{
				Element child = currentElement.getChild(IXmlElements.FORMAT,
						nameSpace);

				if (child != null) {

					termPartObject.setSearchFormat(formatFactory
							.createSearchFormat(checkForSpaces(currentElement
									.getChildText(IXmlElements.FORMAT,
											nameSpace))));

					termPartObject.getSearchFormat().setSearchType(
							child.getAttributeValue(IXmlElements.SEARCH_TYPE));
				}

			}

			{
				Element child = currentElement.getChild(
						IXmlElements.FORMATTED_POINTER, nameSpace);

				if (child != null) {

					termPartObject
							.setSearchFormattedPointer(formattedPointerFactory
									.createSearchFormattedPointer(checkForSpaces(currentElement
											.getChildText(
													IXmlElements.FORMATTED_POINTER,
													nameSpace))));

					termPartObject.getSearchFormattedPointer().setSearchType(
							child.getAttributeValue(IXmlElements.SEARCH_TYPE));
				}

			}

			termPartList.add(termPartObject);

		}
		return termPartList;
	}

	public String checkForSpaces(String value) {
		return (value == null ? null : value.trim());
	}

}
