package edu.asu.qstore4s.controller;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.HashMap;

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

import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;
import edu.asu.qstore4s.repository.AppellationEventRepository;
import edu.asu.qstore4s.repository.IElementNeo4jConnector;
import edu.asu.qstore4s.repository.RelationEventRepository;
import edu.asu.qstore4s.service.IRepositoryManager;
import edu.asu.qstore4s.service.IStatistics;
import edu.asu.qstore4s.service.impl.Statistics;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class QStoreStatisticsTest {
    
    @Mock
    private AppellationEventRepository appeventrep;
    
    @Mock
    private RelationEventRepository releventrep;
    
    @Mock
    private IElementNeo4jConnector connector;
    
    @InjectMocks
    private  Statistics stat;
    
    @Before
    public void setup(){
        
        appeventrep=Mockito.mock(AppellationEventRepository.class);
        releventrep=Mockito.mock(RelationEventRepository.class);
        connector=Mockito.mock(IElementNeo4jConnector.class);
        
        MockitoAnnotations.initMocks(this);
        
    }
    
    @Test
    public void testGetStatistics() throws InvalidDataException{
        when(appeventrep.getAppellationEventCount()).thenReturn(5);
        when(releventrep.getRelationEventCount()).thenReturn(2);
        when(connector.getElementCount()).thenReturn(26);
        
        HashMap<String, Integer> statisticsMap = new HashMap<String, Integer>(); 
        statisticsMap=stat.getStatistics();
        
        assertEquals(Integer.valueOf(5), Integer.valueOf(statisticsMap.get(IStatistics.APPELLATIONS)));
        assertEquals(Integer.valueOf(2), Integer.valueOf(statisticsMap.get(IStatistics.RELATIONS)));
        assertEquals(Integer.valueOf(26), Integer.valueOf(statisticsMap.get(IStatistics.NODES)));
        assertEquals(Integer.valueOf(3), Integer.valueOf(statisticsMap.size()));
    }
    

}
