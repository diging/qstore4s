package edu.asu.qstore4s.converter;

import java.util.List;

import org.json.JSONException;

import edu.asu.qstore4s.domain.elements.impl.Element;
import edu.asu.qstore4s.domain.events.impl.CreationEvent;

/**
 * This file contains the interface class for Converter class which has the
 * following methods. convertToXML and convertToJson
 * 
 * @author Bhargav Desai
 * 
 */
public interface IConverter {

	/**
	 * This method is used to convert list of creation event into XML.
	 * 
	 * @param creationEventList
	 * @return XML
	 */
	String convertToXML(List<CreationEvent> creationEventList);

	/**
	 * This method is used to convert list of creation event into JSON
	 * 
	 * @param creationEventList
	 * @return JSON
	 * @throws JSONException
	 */
	String convertToJson(List<CreationEvent> creationEventList)
			throws JSONException;

	/**
	 * This method is used to convert element into JSON.
	 * 
	 * @param element
	 * @return JSON
	 * @throws JSONException
	 */

	String convertToJson(Element element) throws JSONException;

	/**
	 * This method is used to convert element into XML.
	 * 
	 * @param element
	 * @return XML
	 */
	String convertToXML(Element element);

	/**
	 * This is shallow convert method which convert element to XML.
	 * 
	 * @param element
	 * @return XML
	 */
	String convertToXMLShallow(Element element);

	/**
	 * This is shallow convert method which convert element to JSON.
	 * 
	 * @param element
	 * @return JSON
	 * @throws JSONException
	 */
	String convertToJsonShallow(Element element) throws JSONException;

}
