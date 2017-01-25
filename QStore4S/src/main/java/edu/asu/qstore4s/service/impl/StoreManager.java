package edu.asu.qstore4s.service.impl;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.db.neo4j.IDbConnector;
import edu.asu.qstore4s.db.neo4j.IStoreObjectsToDb;
import edu.asu.qstore4s.domain.elements.impl.Element;
import edu.asu.qstore4s.domain.events.impl.CreationEvent;
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
    public List<CreationEvent> insertIntoDb(List<List<Element>> creationEventList)
            throws URISyntaxException, InvalidDataException {

        List<CreationEvent> creationEventListwithID = new ArrayList<CreationEvent>();

        creationEventListwithID = storeObjectsToDb.writeObjectsToDb(creationEventList);

        return creationEventListwithID;

    }

    /**
     * {@inheritDoc}
     */

    @Override
    public List<CreationEvent> searchRelationFromDb(List<List<Element>> creationEventList)
            throws URISyntaxException, InvalidDataException {

        List<CreationEvent> creationEventListwithID = new ArrayList<CreationEvent>();

        creationEventListwithID = dbConnector.searchRelationInDb(creationEventList.get(0));

        return creationEventListwithID;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Element getObjectFromDb(String id) throws InvalidDataException {
        return dbConnector.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CreationEvent> searchObjectFromDb(ISearchCreationEvent queryObject) throws InvalidDataException {
        List<CreationEvent> elementList = dbConnector.searchFromDb(queryObject);
        return elementList;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CreationEvent> getObjectFromDb(List<String> idList) throws InvalidDataException {
        return dbConnector.get(idList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CreationEvent> executeQuery(String query, Class<?> clas) {
        return dbConnector.executeQuery(query, clas);
    }

}
