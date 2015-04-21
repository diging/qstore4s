package edu.asu.qstore4s.converter.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;

import edu.asu.qstore4s.domain.elements.impl.Term;
import edu.asu.qstore4s.domain.elements.impl.TermPart;
import edu.asu.qstore4s.domain.elements.impl.TermParts;
import edu.asu.qstore4s.domain.events.impl.AppellationEvent;
import edu.asu.qstore4s.domain.events.impl.RelationEvent;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;

public abstract class AXmlParser {



	public String checkForSpaces(String value) {
		return (value == null ? null : value.trim());
	}

	
	
	public abstract List<List<edu.asu.qstore4s.domain.elements.impl.Element>> parseXML(String xml) throws ParserException,
	IOException, URISyntaxException, ParseException,
	InvalidDataException;
	
	public abstract RelationEvent getRelationEvent(Element relationEvent,
			Namespace nameSpace, List<edu.asu.qstore4s.domain.elements.impl.Element> referencedObjectList)
			throws ParserException, IOException, URISyntaxException,
			ParseException, InvalidDataException;
	
	
	
	
	public abstract AppellationEvent getAppellationEvent(Element appellationEvent,
			Namespace nameSpace, List<edu.asu.qstore4s.domain.elements.impl.Element> referencedObjectList)
			throws ParserException, IOException, URISyntaxException,
			ParseException, InvalidDataException;
	

	
	public abstract Term getTermObject(Element term, Namespace nameSpace,
			List<edu.asu.qstore4s.domain.elements.impl.Element> referencedObjectList) throws ParseException, InvalidDataException ;
	
	public abstract TermParts getTermPartsObject(Element printedRepresentation,
			Namespace nameSpace, List<edu.asu.qstore4s.domain.elements.impl.Element> referencedObjectList)
			throws ParseException ;
	

	public abstract Set<TermPart> getTermPartObject(Set<Element> TermPartNodes,
			Namespace nameSpace, List<edu.asu.qstore4s.domain.elements.impl.Element> referencedObjectList)
			throws ParseException;
	
	
	
}
