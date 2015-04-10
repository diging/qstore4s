package edu.asu.qstore4s.service.impl;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.db.neo4j.IDbConnector;
import edu.asu.qstore4s.db.neo4j.IStoreObjectsToDb;
import edu.asu.qstore4s.domain.elements.IElement;
import edu.asu.qstore4s.domain.events.ICreationEvent;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.search.events.ISearchCreationEvent;
import edu.asu.qstore4s.service.IStoreManager;

/**
 * 
 * @author Bhargav Desai
 * 
 */
@Service
public class StoreManager implements IStoreManager {

	@Autowired
	IStoreObjectsToDb storeObjectsToDb;

	@Autowired
	IDbConnector dbConnector;

	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<ICreationEvent> insertIntoDb(
			List<List<IElement>> creationEventList) throws URISyntaxException,
			InvalidDataException {

		List<ICreationEvent> creationEventListwithID = new ArrayList<ICreationEvent>();

		creationEventListwithID = storeObjectsToDb
				.writeObjectsToDb(creationEventList);

		return creationEventListwithID;

	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<ICreationEvent> searchRelationFromDb(
			List<List<IElement>> creationEventList) throws URISyntaxException,
			InvalidDataException {

		List<ICreationEvent> creationEventListwithID = new ArrayList<ICreationEvent>();

		creationEventListwithID = dbConnector
				.searchRelationInDb(creationEventList.get(0));

		return creationEventListwithID;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IElement getObjectFromDb(String id) throws InvalidDataException {
		IElement element = dbConnector.get(id);

		return element;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ICreationEvent> searchObjectFromDb(
			ISearchCreationEvent queryObject) throws InvalidDataException {
		List<ICreationEvent> elementList = dbConnector
				.searchFromDb(queryObject);
		return elementList;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ICreationEvent> getObjectFromDb(List<String> idList)
			throws InvalidDataException {
		return dbConnector.get(idList);
	}

}
