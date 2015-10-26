package edu.asu.qstore4s.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.events.impl.AppellationEvent;
import edu.asu.qstore4s.domain.events.impl.CreationEvent;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.repository.AppellationEventRepository;
import edu.asu.qstore4s.repository.ElementNeo4jConnector;
import edu.asu.qstore4s.repository.IElementNeo4jConnector;
import edu.asu.qstore4s.repository.RelationEventRepository;
import edu.asu.qstore4s.service.IStatistics;

/**
 * A Service to get the statistics from QStore
 * 
 * @author Nikhil Aourpally, Rachita Satyasi.
 */
@Service
public class Statistics implements IStatistics {

    @Autowired
    private AppellationEventRepository appeventrep;

    @Autowired
    private RelationEventRepository releventrep;

    @Autowired
    private IElementNeo4jConnector connector;

    /**
     * The method returns the statistics fetched from the database in a HashMap
     *
     * @return a HashMap containing the statistics
     * @throws InvalidDataException
     */
    @Override
    public HashMap<String, Integer> getStatistics() throws InvalidDataException {

        // I will fetch the statistics using a Neo4j connecter here

        HashMap<String, Integer> statisticsMap = new HashMap<String, Integer>();
        statisticsMap.put(IStatistics.NODES, connector.getElementCount());
        statisticsMap.put(IStatistics.RELATIONS,
                releventrep.getRelationEventCount());
        statisticsMap.put(IStatistics.APPELLATIONS,
                appeventrep.getAppellationEventCount());
        return statisticsMap;
    }

}
