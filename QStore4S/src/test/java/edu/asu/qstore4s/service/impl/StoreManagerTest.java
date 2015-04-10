package edu.asu.qstore4s.service.impl;

import static org.mockito.Mockito.when;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.asu.qstore4s.db.neo4j.IDbConnector;
import edu.asu.qstore4s.db.neo4j.IStoreObjectsToDb;
import edu.asu.qstore4s.domain.elements.IElement;
import edu.asu.qstore4s.domain.events.ICreationEvent;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.service.IStoreManager;

public class StoreManagerTest {

	
	@InjectMocks
	IStoreManager storemanager =  new StoreManager();
	
	@Mock
	IStoreObjectsToDb storeObjectsToDb;
	
	@Mock
	IDbConnector dbConnector;
	
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testInsertIntoDb() throws URISyntaxException, InvalidDataException {
		
		List<ICreationEvent> creationEventListwithID = new ArrayList<ICreationEvent>();
		List<List<IElement>> list = new ArrayList<List<IElement>>();
		
		when(storeObjectsToDb.writeObjectsToDb(list)).thenReturn(creationEventListwithID);
		
		Assert.assertEquals(storemanager.insertIntoDb(list), creationEventListwithID);
		
		
	}
	
	
	@Test
	public void testSearchRelationFromDb() throws URISyntaxException, InvalidDataException {
		
		List<ICreationEvent> creationEventListwithID = new ArrayList<ICreationEvent>();
		
		
		List<List<IElement>> list = new ArrayList<List<IElement>>();

		List<IElement> elementList = new ArrayList<IElement>();
		
		list.add(elementList);
		when(dbConnector.searchRelationInDb(elementList)).thenReturn(creationEventListwithID);
		
		Assert.assertEquals(storemanager.searchRelationFromDb(list), creationEventListwithID);
		
		
	}
	
	
	
	

}
