package edu.asu.qstore4s.converter.impl;


import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.asu.qstore4s.converter.IXmlElements;
import edu.asu.qstore4s.domain.elements.factory.impl.ActorFactory;
import edu.asu.qstore4s.domain.elements.factory.impl.ConceptFactory;
import edu.asu.qstore4s.domain.elements.factory.impl.PlaceFactory;
import edu.asu.qstore4s.domain.elements.factory.impl.RelationFactory;
import edu.asu.qstore4s.domain.elements.factory.impl.SourceReferenceFactory;
import edu.asu.qstore4s.domain.elements.factory.impl.TermFactory;
import edu.asu.qstore4s.domain.elements.factory.impl.TermPartFactory;
import edu.asu.qstore4s.domain.elements.factory.impl.TermPartsFactory;
import edu.asu.qstore4s.domain.elements.factory.impl.VocabularyEntryFactory;
import edu.asu.qstore4s.domain.elements.impl.Actor;
import edu.asu.qstore4s.domain.elements.impl.Concept;
import edu.asu.qstore4s.domain.elements.impl.Element;
import edu.asu.qstore4s.domain.elements.impl.Place;
import edu.asu.qstore4s.domain.elements.impl.Relation;
import edu.asu.qstore4s.domain.elements.impl.SourceReference;
import edu.asu.qstore4s.domain.elements.impl.Term;
import edu.asu.qstore4s.domain.elements.impl.TermPart;
import edu.asu.qstore4s.domain.elements.impl.TermParts;
import edu.asu.qstore4s.domain.elements.impl.VocabularyEntry;
import edu.asu.qstore4s.domain.events.factory.impl.AppellationEventFactory;
import edu.asu.qstore4s.domain.events.factory.impl.RelationEventFactory;
import edu.asu.qstore4s.domain.events.impl.AppellationEvent;
import edu.asu.qstore4s.domain.events.impl.RelationEvent;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;


public class XMLtoObjectTest {

	@Mock
	RelationEventFactory relationEventFactory;
	
	@Mock
	ActorFactory actorFactory;
	@Mock
	ConceptFactory conceptFactory;
	@Mock
	PlaceFactory placeFactory;
	@Mock
	RelationFactory relationFactory;
	@Mock
	SourceReferenceFactory sourceReferenceFactory;
	@Mock
	TermFactory termFactory;
	@Mock
	TermPartFactory termPartFactory;
	@Mock
	TermPartsFactory termPartsFactory;
	@Mock
	VocabularyEntryFactory vocabularyEntryFactory;
	
	@Mock
	AppellationEventFactory appellationEventFactory;
	
	Actor actor1;
	Actor actor2;
	Actor actor3;
	Concept concept;
	Relation relation;
	Place place;
	Term term;
	TermPart termPart;
	TermParts termParts;
	VocabularyEntry vocabularyEntry1;
	VocabularyEntry vocabularyEntry2;
	SourceReference sourceReference;
	RelationEvent relationEvent;
	AppellationEvent appellationEvent;
	
	
	
	@InjectMocks
	XmltoObject xmlToObject = new XmltoObject();
	
	
	
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);

		
	actor1 = new Actor();
	actor1.setSourceURI("creator1");
	actor2 = new Actor();
	actor2.setSourceURI("test_interpretation_creator");
	actor3 = new Actor();
	actor3.setSourceURI("test_relation_creator");

	
	concept = new Concept();
	concept.setSourceURI("URI1");
	relation = new Relation();
	place =  new Place();
	place.setSourceURI("berlin");
	term = new Term();
	termPart = new TermPart();
	termParts = new TermParts();
	vocabularyEntry1 = new VocabularyEntry();
	vocabularyEntry1.setSourceURI("URI2");
	vocabularyEntry2 = new VocabularyEntry();
	vocabularyEntry2.setSourceURI("URI1");
	relationEvent = new RelationEvent();
	appellationEvent = new AppellationEvent();
	sourceReference = new SourceReference();
	sourceReference.setSourceURI("test_source_reference");
	
	
		
		
	}

	@Test
	public void testParseXML() throws ParserException, IOException, URISyntaxException, ParseException, InvalidDataException {
		
		when(relationEventFactory.createRelationEvent()).thenReturn(relationEvent);
		
		when(actorFactory.createActor("creator1")).thenReturn(actor1);
		when(actorFactory.createActor("test_interpretation_creator")).thenReturn(actor2);
		when(actorFactory.createActor("test_relation_creator")).thenReturn(actor3);
		when(conceptFactory.createConcept("URI1")).thenReturn(concept);
		when(relationFactory.createRelation()).thenReturn(relation);
		when(placeFactory.createPlace("berlin")).thenReturn(place);
		when(termFactory.createTerm()).thenReturn(term);
		when(termPartFactory.createTermPart()).thenReturn(termPart);
		when(vocabularyEntryFactory.createVocabularyEntry("URI1")).thenReturn(vocabularyEntry1);
		when(vocabularyEntryFactory.createVocabularyEntry("URI2")).thenReturn(vocabularyEntry2);
		when(appellationEventFactory.createAppellationEvent()).thenReturn(appellationEvent);
		when(sourceReferenceFactory.createSourceReference("test_source_reference")).thenReturn(sourceReference);
		when(termPartsFactory.createTermParts()).thenReturn(termParts);
		
		
		String content = new Scanner(new File("src/test/resources/input-example.xml")).useDelimiter("\\Z").next();
		List<List<Element>> creationEventList = xmlToObject.parseXML(content);
		List<Element> objectsList = creationEventList.get(0);
		DateFormat formatter = new SimpleDateFormat(IXmlElements.DATE_FORMAT);
	
		
		//checking size of relationevent list
        Assert.assertEquals(1, objectsList.size());
		RelationEvent element = (RelationEvent)objectsList.get(0);
		Assert.assertEquals("creator1", element.getCreator().getSourceURI());
		Assert.assertEquals(formatter.parse("2002-05-30T09:00:00").toString(), element.getCreationDate().toString());
		Assert.assertEquals("berlin", element.getCreationPlace().getSourceURI());
		Assert.assertEquals("test_source_reference", element.getSourceReference().getSourceURI());
		Assert.assertEquals("test_interpretation_creator", element.getInterpretationCreator().getSourceURI());
		Assert.assertEquals("test_relation_creator", element.getRelationCreator().getSourceURI());
		
		Relation relation = (Relation)element.getRelation();
		Assert.assertEquals("creator1", relation.getCreator().getSourceURI());
		Assert.assertEquals(formatter.parse("2002-05-30T09:00:00").toString(), relation.getCreationDate().toString());
		Assert.assertEquals("berlin", relation.getCreationPlace().getSourceURI());
		
		AppellationEvent appellation = (AppellationEvent)relation.getSubject();
		Assert.assertEquals("creator1", appellation.getCreator().getSourceURI());
		Assert.assertEquals(formatter.parse("2002-05-30T09:00:00").toString(), appellation.getCreationDate().toString());
		Assert.assertEquals("berlin", appellation.getCreationPlace().getSourceURI());
		Assert.assertEquals("test_source_reference", appellation.getSourceReference().getSourceURI());
		
		Term term = appellation.getTerm();
		Assert.assertEquals("creator1", term.getCreator().getSourceURI());
		Assert.assertEquals(formatter.parse("2002-05-30T09:00:00").toString(), term.getCreationDate().toString());
		Assert.assertEquals("berlin", term.getCreationPlace().getSourceURI());
		Assert.assertEquals("test_source_reference", term.getSourceReference().getSourceURI());
		Assert.assertEquals("URI1", term.getNormalizedRepresentation().getSourceURI());
		Assert.assertEquals(true, term.isCertain());
		
		TermParts termParts = term.getPrintedRepresentation();
		Assert.assertEquals("creator1", termParts.getCreator().getSourceURI());
		Assert.assertEquals(formatter.parse("2002-05-30T09:00:00").toString(), termParts.getCreationDate().toString());
		Assert.assertEquals("berlin", termParts.getCreationPlace().getSourceURI());
		Assert.assertEquals("test_source_reference", termParts.getReferencedSource().getSourceURI());
		
		Set<TermPart> termpartList =  termParts.getTermParts();
		Iterator<TermPart> termPartIterator = termpartList.iterator();
		while(termPartIterator.hasNext()){
			TermPart termPart = termPartIterator.next();
			Assert.assertEquals("creator1", termPart.getCreator().getSourceURI());
			Assert.assertEquals(formatter.parse("2002-05-30T09:00:00").toString(), termPart.getCreationDate().toString());
			Assert.assertEquals("berlin", termPart.getCreationPlace().getSourceURI());
			Assert.assertEquals("test_source_reference", termPart.getSourceReference().getSourceURI());
			Assert.assertEquals("Exp1", termPart.getExpression());
			Assert.assertEquals("URI2", termPart.getNormalization().getSourceURI());
			
			
			
		}
			
				
			
		


		
		
		
		
		
		
	}

}
