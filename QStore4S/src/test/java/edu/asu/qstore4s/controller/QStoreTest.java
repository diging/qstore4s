package edu.asu.qstore4s.controller;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;
import edu.asu.qstore4s.service.IRepositoryManager;
import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class QStoreTest {

    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;
    @Mock
    IRepositoryManager repositoryManager;

    @InjectMocks
    QStore qStore;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        // qStore = new QStore();

        when(repositoryManager.processXMLandStoretoDb("test", "application/xml")).thenReturn("inxml");
        when(repositoryManager.processXMLandStoretoDb("test", "application/json")).thenReturn("injson");

        when(repositoryManager.getFull("test", "application/json")).thenReturn("inGetJsonFull");

        when(repositoryManager.getFull("test", "application/xml")).thenReturn("inGetXmlFull");

        when(repositoryManager.getShallow("test", "application/json")).thenReturn("inGetJsonShallow");

        when(repositoryManager.getShallow("test", "application/xml")).thenReturn("inGetXmlShallow");

        when(repositoryManager.search("test", "application/xml")).thenReturn("inSearchXml");

        when(repositoryManager.search("test", "application/json")).thenReturn("inSearchXml");

    }

    @Test
    public void testProcessXML() throws ParserException, IOException, URISyntaxException, ParseException, JSONException,
            InvalidDataException {

        // Testing for different accept values

        // accept=application/xml
        {
            String returnString = qStore.processXML(request, response, "test", "application/xml");
            Assert.assertEquals("inxml", returnString);
        }
        // accept=application/json
        {
            String returnString = qStore.processXML(request, response, "test", "application/json");
            Assert.assertEquals("injson", returnString);
        }

        // accept=random
        // be deafult json should be returned.
        {
            String returnString = qStore.processXML(request, response, "test", "random");
            Assert.assertEquals("inxml", returnString);
        }

        // accept=null
        // by default json should be returned.

        {
            String returnString = qStore.processXML(request, response, "test", null);
            // Assert.assertEquals("injson", returnString);
        }

    }

    @Test
    public void testGetData() throws ParserException, IOException, URISyntaxException, ParseException, JSONException,
            InvalidDataException {

        // Testing for different accept and shallow values

        // accept=application/xml
        // shallow=true

        {
            ResponseEntity<String> returnString = qStore.getData(request, "application/xml", "true", "test");
            Assert.assertEquals("inGetXmlShallow", returnString.getBody());
        }
        // accept=application/json
        // shallow = true
        {
            ResponseEntity<String> returnString = qStore.getData(request, "application/json", "true", "test");
            Assert.assertEquals("inGetJsonShallow", returnString.getBody());
        }

        // accept=application/xml
        // shallow=false
        {
            ResponseEntity<String> returnString = qStore.getData(request, "application/xml", "false", "test");
            Assert.assertEquals("inGetXmlFull", returnString.getBody());
        }

        // accept=application/json
        // shallow=false

        {
            ResponseEntity<String> returnString = qStore.getData(request, "application/json", "false", "test");
            Assert.assertEquals("inGetJsonFull", returnString.getBody());
        }

        // accept=application/json
        // shallow=random
        // for any shallow random value full method should be called

        {
            ResponseEntity<String> returnString = qStore.getData(request, "application/json", "random", "test");
            Assert.assertEquals("inGetJsonFull", returnString.getBody());
        }

        // accept=application/json
        // shallow=null
        // for shallow null full method should be called

        {
            ResponseEntity<String> returnString = qStore.getData(request, "application/json", null, "test");
            Assert.assertEquals("inGetJsonFull", returnString.getBody());
        }

    }

    @Test
    public void testSearch() throws ParserException, IOException, URISyntaxException, ParseException, JSONException,
            InvalidDataException {

        {
            String returnString = qStore.search(request, response, "test", "application/xml");
            Assert.assertEquals("inSearchXml", returnString);
        }

        {
            String returnString = qStore.search(request, response, "test", "application/json");
            Assert.assertEquals("inSearchXml", returnString);
        }

        {
            String returnString = qStore.search(request, response, "", "application/json");
            Assert.assertEquals("{ \"message\" : \"Please provide XML in body of the post request.\" }", returnString);
        }

        {
            String returnString = qStore.search(request, response, "  ", "application/xml");
            Assert.assertEquals("<message>Please provide XML in body of the post request.</message>", returnString);
        }

    }

}
