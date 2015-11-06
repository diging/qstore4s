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
import org.springframework.data.neo4j.conversion.QueryResultBuilder;
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

/**
 * Test class to test the getElementCount() method of the ElementNeo4jConnector
 * class
 * 
 * @author Rachita Satyasi
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class QStoreElementConnectorTest {

    @Mock
    private Neo4jTemplate template;
    @Mock
    Result<Map<String, Object>> nodes;

    @InjectMocks
    private ElementNeo4jConnector conn;

    @Before
    public void setup() {

        template = Mockito.mock(Neo4jTemplate.class);
        nodes = Mockito.mock(Result.class);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testgetElementCount() {
        Map<String, Object> params = new HashMap<String, Object>();
        when(template.query("match n return count(n)", params)).thenReturn(
                nodes);
        Map<String, Object> count = new HashMap<String, Object>();
        count.put("count(n)", 26);
        when(nodes.single()).thenReturn(count);
        int nodecount = 0;
        nodecount = conn.getElementCount();

        assertEquals(count.get("count(n)"), nodecount);
    }

}
