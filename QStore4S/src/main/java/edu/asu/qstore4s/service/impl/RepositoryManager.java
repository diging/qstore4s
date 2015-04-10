/**
 * 
 */
package edu.asu.qstore4s.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.converter.IConverter;
import edu.asu.qstore4s.converter.IGetConverter;
import edu.asu.qstore4s.converter.ISearchXmlParser;
import edu.asu.qstore4s.converter.IXmltoObject;
import edu.asu.qstore4s.domain.elements.IElement;
import edu.asu.qstore4s.domain.events.ICreationEvent;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;
import edu.asu.qstore4s.search.events.ISearchCreationEvent;
import edu.asu.qstore4s.service.IRepositoryManager;
import edu.asu.qstore4s.service.IStoreManager;

/**
 * This class calls XML parser to parse XMl and convert it to object as well as
 * call StoreManager to store objects into the database.
 * 
 * @author Bhargav Desai
 */

@Service
public class RepositoryManager implements IRepositoryManager {

	private static final Logger logger = LoggerFactory
			.getLogger(RepositoryManager.class);

	private static final String JSON = "application/json";

	@Autowired
	IStoreManager storeManager;

	@Autowired
	IXmltoObject xmlToObject;

	@Autowired
	IConverter converter;

	@Autowired
	ISearchXmlParser searchXmlParser;

	@Autowired
	IGetConverter getConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String processXMLandStoretoDb(String xml, String type)
			throws URISyntaxException, ParserException, IOException,
			ParseException, JSONException, InvalidDataException {

		logger.info("inside processXML method of repository manager");

		List<List<IElement>> creationEventList = new ArrayList<List<IElement>>();
		List<ICreationEvent> creationEventListwithID = new ArrayList<ICreationEvent>();

		creationEventList = xmlToObject.parseXML(xml);

		creationEventListwithID = storeManager.insertIntoDb(creationEventList);

		if (type.equals((JSON))) {
			return converter.convertToJson(creationEventListwithID);
		} else {
			return converter.convertToXML(creationEventListwithID);
		}

	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public String searchByAppellationId(String xml, String type)
			throws URISyntaxException, ParserException, IOException,
			ParseException, JSONException, InvalidDataException {

		logger.info("inside searchXML method of repository manager");

		List<List<IElement>> creationEventList = new ArrayList<List<IElement>>();
		List<ICreationEvent> creationEventListwithID = new ArrayList<ICreationEvent>();

		creationEventList = xmlToObject.parseXML(xml);

		creationEventListwithID = storeManager
				.searchRelationFromDb(creationEventList);

		if (type.equals(JSON)) {
			return converter.convertToJson(creationEventListwithID);
		} else {
			return converter.convertToXML(creationEventListwithID);
		}

	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public String search(String xml, String type) throws URISyntaxException,
			ParserException, IOException, ParseException, JSONException,
			InvalidDataException {

		logger.info("inside search method of repository manager");

		ISearchCreationEvent queryObject = searchXmlParser.parseXML(xml);

		List<ICreationEvent> elementList = storeManager
				.searchObjectFromDb(queryObject);

		if (type.equals(JSON)) {
			return converter.convertToJson(elementList);
		} else {
			return converter.convertToXML(elementList);
		}
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public String getShallow(String id, String type) throws JSONException,
			InvalidDataException {

		IElement element = storeManager.getObjectFromDb(id);
		if (type.equals(JSON)) {
			return converter.convertToJsonShallow(element);
		} else {
			return converter.convertToXMLShallow(element);
		}

	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public String getFull(String id, String type) throws JSONException,
			InvalidDataException {

		IElement element = storeManager.getObjectFromDb(id);
		;
		// creationEventList.add(creationEvent);
		if (type.equals(JSON)) {
			return converter.convertToJson(element);
		} else {
			return converter.convertToXML(element);
		}

	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public String getList(String xml, String accept) throws ParserException,
			IOException, URISyntaxException, ParseException,
			InvalidDataException, JSONException {

		logger.info("inside get method of repository manager");

		List<String> idList = getConverter.parseXML(xml);

		List<ICreationEvent> elementList = storeManager.getObjectFromDb(idList);

		if (accept.equals(JSON)) {
			return converter.convertToJson(elementList);
		} else {
			return converter.convertToXML(elementList);
		}

	}
}
