package edu.asu.qstore4s.db.neo4j.impl;


import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.asu.qstore4s.domain.events.impl.AppellationEvent;
import edu.asu.qstore4s.domain.events.impl.RelationEvent;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.repository.AppellationEventRepository;
import edu.asu.qstore4s.repository.RelationEventRepository;

public class GetObjectsFromDbTest {

	@InjectMocks
	DbConnector getObjgetsFromDb  = new DbConnector();
	
	@Mock
	RelationEventRepository relatEventRepository;
	
	@Mock
	AppellationEventRepository appellationEventRepository;
	
	RelationEvent relationEvent;
	AppellationEvent appellationEvent;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		relationEvent = new RelationEvent();
		appellationEvent = new AppellationEvent();

	}

	@Test
	public void testGet() {
		
		//checking for valid  relation event id
		
		when(relatEventRepository.findById("REL_EVN123")).thenReturn(relationEvent);
	
		try {
			Assert.assertNotNull(getObjgetsFromDb.get("REL_EVN123"));
		} catch (InvalidDataException e) {
			
		  Assert.fail("not valid id");
		}
		
		//checking for valid appelation event id
		
		when(appellationEventRepository.findById("APP_EVN123")).thenReturn(appellationEvent);
		
		try {
			Assert.assertNotNull(getObjgetsFromDb.get("APP_EVN123"));
		} catch (InvalidDataException e) {
			
		  Assert.fail("not valid id");
		}
		
		
		//checking for invalid id
		
		try {
			Assert.assertNotNull(getObjgetsFromDb.get("Test"));
		} catch (InvalidDataException e) {
			
		  //test passes/
		}
		
		
		
		
	}

}
