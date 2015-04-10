package edu.asu.qstore4s.converter.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;

import edu.asu.qstore4s.domain.elements.IElement;
import edu.asu.qstore4s.domain.elements.ITerm;
import edu.asu.qstore4s.domain.elements.ITermPart;
import edu.asu.qstore4s.domain.elements.ITermParts;
import edu.asu.qstore4s.domain.events.IAppellationEvent;
import edu.asu.qstore4s.domain.events.IRelationEvent;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;

public abstract class AXmlParser {



	public String checkForSpaces(String value) {
		return (value == null ? null : value.trim());
	}

	
	
	public abstract List<List<IElement>> parseXML(String xml) throws ParserException,
	IOException, URISyntaxException, ParseException,
	InvalidDataException;
	
	public abstract IRelationEvent getRelationEvent(Element relationEvent,
			Namespace nameSpace, List<IElement> referencedObjectList)
			throws ParserException, IOException, URISyntaxException,
			ParseException, InvalidDataException;
	
	
	
	
	public abstract IAppellationEvent getAppellationEvent(Element appellationEvent,
			Namespace nameSpace, List<IElement> referencedObjectList)
			throws ParserException, IOException, URISyntaxException,
			ParseException, InvalidDataException;
	

	
	public abstract ITerm getTermObject(Element term, Namespace nameSpace,
			List<IElement> referencedObjectList) throws ParseException, InvalidDataException ;
	
	public abstract ITermParts getTermPartsObject(Element printedRepresentation,
			Namespace nameSpace, List<IElement> referencedObjectList)
			throws ParseException ;
	

	public abstract Set<ITermPart> getTermPartObject(Set<Element> TermPartNodes,
			Namespace nameSpace, List<IElement> referencedObjectList)
			throws ParseException;
	
	
	
}
