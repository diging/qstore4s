package edu.asu.qstore4s.converter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

import edu.asu.qstore4s.domain.elements.IElement;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;

/**
 * This file contains the interface for XmltoObject class
 * which has the following method:
 * parseXML
 * @author Veena Borannagowda
 *
 */
public interface IXmltoObject {

	
	/**
	 * The method parse XML for post request to add relation and appellation node into the database.
	 * @param xml
	 * @return
	 * @throws ParserException
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ParseException
	 * @throws InvalidDataException
	 */
	List<List<IElement>> parseXML(String xml) throws ParserException, IOException, URISyntaxException, ParseException, InvalidDataException;
	
	

}
