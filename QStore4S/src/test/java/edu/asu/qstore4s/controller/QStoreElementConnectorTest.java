package edu.asu.qstore4s.controller;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;
import edu.asu.qstore4s.repository.AppellationEventRepository;
import edu.asu.qstore4s.repository.ElementNeo4jConnector;
import edu.asu.qstore4s.repository.IElementNeo4jConnector;
import edu.asu.qstore4s.repository.RelationEventRepository;
import edu.asu.qstore4s.service.IRepositoryManager;
import edu.asu.qstore4s.service.IStatistics;
import edu.asu.qstore4s.service.impl.Statistics;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class QStoreElementConnectorTest {
    
    @Mock
    private Neo4jTemplate template;
    
    @InjectMocks
    private ElementNeo4jConnector conn;
    
    @Before
    public void setup(){
        
        template=Mockito.mock(Neo4jTemplate.class);
        
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testgetElementCount(){
        Map<String, Object> params = new HashMap<String, Object>();
        Result<HashMap<String, Object>> nodes=(Result<HashMap<String, Object>>) new HashMap<String, Object>();
        ((Map<String, Object>) nodes).put("nodes", 26);
        //when(template.query("",params)).thenReturn(nodes);
    }

}
