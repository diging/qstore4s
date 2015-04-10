package edu.asu.qstore4s.converter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;
import edu.asu.qstore4s.search.events.ISearchCreationEvent;

/**
 * 
 * @author Bhargav Desai
 *
 */
public interface ISearchXmlParser {

	
	/**
	 * THe method parse xml for search method which returns information about relation or appellation events according to given parameters.
	 * @param xml
	 * @return
	 * @throws ParserException
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ParseException
	 * @throws InvalidDataException
	 */
	ISearchCreationEvent parseXML(String xml) throws ParserException, IOException,
			URISyntaxException, ParseException, InvalidDataException;

	
}
