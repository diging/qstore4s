package edu.asu.qstore4s.converter;

import java.util.List;

import org.json.JSONException;

import edu.asu.qstore4s.domain.elements.IElement;
import edu.asu.qstore4s.domain.events.ICreationEvent;

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
	String convertToXML(List<ICreationEvent> creationEventList);

	/**
	 * This method is used to convert list of creation event into JSON
	 * 
	 * @param creationEventList
	 * @return JSON
	 * @throws JSONException
	 */
	String convertToJson(List<ICreationEvent> creationEventList)
			throws JSONException;

	/**
	 * This method is used to convert element into JSON.
	 * 
	 * @param element
	 * @return JSON
	 * @throws JSONException
	 */

	String convertToJson(IElement element) throws JSONException;

	/**
	 * This method is used to convert element into XML.
	 * 
	 * @param element
	 * @return XML
	 */
	String convertToXML(IElement element);

	/**
	 * This is shallow convert method which convert element to XML.
	 * 
	 * @param element
	 * @return XML
	 */
	String convertToXMLShallow(IElement element);

	/**
	 * This is shallow convert method which convert element to JSON.
	 * 
	 * @param element
	 * @return JSON
	 * @throws JSONException
	 */
	String convertToJsonShallow(IElement element) throws JSONException;

}
