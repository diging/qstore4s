package edu.asu.qstore4s.converter;

import java.io.IOException;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;


/**
 * 
 * @author Bhargav Desai
 *
 */
public interface IGetConverter {
	/**
	 * The method parse XML for get request to get information about relation and appellation events. 
	 * @param xml
	 * @return
	 * @throws ParserException
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ParseException
	 * @throws InvalidDataException
	 */

	List<String> parseXML(String xml) throws ParserException, IOException,
			URISyntaxException, ParseException, InvalidDataException;

}
