package edu.asu.qstore4s.service.impl;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.asu.qstore4s.converter.IConverter;
import edu.asu.qstore4s.converter.IGetConverter;
import edu.asu.qstore4s.converter.ISearchXmlParser;
import edu.asu.qstore4s.converter.IXmltoObject;
import edu.asu.qstore4s.domain.elements.impl.Element;
import edu.asu.qstore4s.domain.events.impl.CreationEvent;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;
import edu.asu.qstore4s.search.events.ISearchCreationEvent;
import edu.asu.qstore4s.search.events.impl.SearchRelationEvent;
import edu.asu.qstore4s.service.IRepositoryManager;
import edu.asu.qstore4s.service.IStoreManager;

public class RepositoryManagerTest {

	@Mock
	HttpServletResponse response;
	@Mock
	HttpServletRequest request;
	@Mock
	IStoreManager storeManager;
	@Mock
	IConverter converter;
	@Mock
	IXmltoObject xmltoObject;
	@Mock
	IGetConverter getConverter;
	
	@Mock
	ISearchXmlParser serachXmlParser;
	
	
	List<List<Element>> creationEventList;
	List<CreationEvent> creationEventListwithID;
	Element element;

	@InjectMocks
	IRepositoryManager repositoryManager = new RepositoryManager();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		creationEventList = new ArrayList<List<Element>>();
		creationEventListwithID = new ArrayList<CreationEvent>();
		element = new Element();

	}

	@Test
	public void testProcessXMLandStoretoDb() throws URISyntaxException,
			ParserException, IOException, ParseException, JSONException, InvalidDataException {

		when(xmltoObject.parseXML("test")).thenReturn(creationEventList);
		when(storeManager.insertIntoDb(creationEventList)).thenReturn(
				creationEventListwithID);
		when(converter.convertToXML(creationEventListwithID)).thenReturn("xml");

		// accept value = xml
		// the method should return converted XML with id

		{
			String returnstring = repositoryManager.processXMLandStoretoDb(
					"test", "application/xml");
			Assert.assertEquals("xml", returnstring);
		}

		when(converter.convertToJson(creationEventListwithID)).thenReturn(
				"json");

		// accept value = json
		// the method should return converted XML with id

		{
			String returnstring = repositoryManager.processXMLandStoretoDb(
					"test", "application/json");
			Assert.assertEquals("json", returnstring);
		}

	}

	@Test
	public void testGetShallow() throws JSONException, InvalidDataException {

		when(storeManager.getObjectFromDb("test")).thenReturn(
				element);
		when(converter.convertToJsonShallow(element)).thenReturn("json");
		when(converter.convertToXMLShallow(element)).thenReturn("xml");

		//accept=json
		
		{
			String returnstring = repositoryManager.getShallow(
					"test", "application/json");
			Assert.assertEquals("json", returnstring);
		}
		
		//accept=xml
	
		{
			String returnstring = repositoryManager.getShallow(
					"test", "application/xml");
			Assert.assertEquals("xml", returnstring);
		}
	
	}

	@Test
	public void testGetFull() throws JSONException, InvalidDataException {
		
		when(storeManager.getObjectFromDb("test")).thenReturn(
				element);
		when(converter.convertToJson(element)).thenReturn("json");
		when(converter.convertToXML(element)).thenReturn("xml");

		//accept=json
		
		{
			String returnstring = repositoryManager.getFull(
					"test", "application/json");
			Assert.assertEquals("json", returnstring);
		}
		
		//accept=xml
	
		{
			String returnstring = repositoryManager.getFull(
					"test", "application/xml");
			Assert.assertEquals("xml", returnstring);
		}

	}

	
	@Test
	public void testgetList() throws JSONException, InvalidDataException, ParserException, IOException, URISyntaxException, ParseException {
		
		
		List<String> idList = new ArrayList<String>();
		List<CreationEvent> icreationEventList=new ArrayList<CreationEvent>();
		
		when(storeManager.getObjectFromDb(idList)).thenReturn(
				icreationEventList);
		
		when(getConverter.parseXML("test")).thenReturn(
				idList);
		
		
		when(converter.convertToJson(icreationEventList)).thenReturn("json");
		when(converter.convertToXML(icreationEventList)).thenReturn("xml");

		//accept=json
		
		{
			String returnstring = repositoryManager.getList(
					"test", "application/json");
			Assert.assertEquals("json", returnstring);
		}
		
		//accept=xml
	
		{
			String returnstring = repositoryManager.getList(
					"test", "application/xml");
			Assert.assertEquals("xml", returnstring);
		}

	}	

	@Test
	public void testgetSearch() throws JSONException, InvalidDataException, ParserException, IOException, URISyntaxException, ParseException {
		
		
	
		ISearchCreationEvent icreationEvent= new SearchRelationEvent();
		List<CreationEvent> icreationEventList=new ArrayList<CreationEvent>();
		
		when(storeManager.searchObjectFromDb(icreationEvent)).thenReturn(
				icreationEventList);
		
		when(serachXmlParser.parseXML("test")).thenReturn(
				icreationEvent);
		
		
		when(converter.convertToJson(icreationEventList)).thenReturn("json");
		when(converter.convertToXML(icreationEventList)).thenReturn("xml");

		//accept=json
		
		{
			String returnstring = repositoryManager.getList(
					"test", "application/json");
			Assert.assertEquals("json", returnstring);
		}
		
		//accept=xml
	
		{
			String returnstring = repositoryManager.getList(
					"test", "application/xml");
			Assert.assertEquals("xml", returnstring);
		}

	}	

	
	
}
