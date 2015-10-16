package edu.asu.qstore4s.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.events.impl.AppellationEvent;
import edu.asu.qstore4s.domain.events.impl.CreationEvent;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.repository.AppellationEventRepository;
import edu.asu.qstore4s.repository.Element;
import edu.asu.qstore4s.repository.RelationEventRepository;
import edu.asu.qstore4s.service.IStatistics;

/**
 * 
 * @author Nikhil Aourpally
 * A Service to get the statistics from QStore
 */

@Service
public class Statistics implements  IStatistics{

	/**
	 * The method returns the statistics fetched from the database in a HashMap
	 *
	 * @return
	 * @throws InvalidDataException 
	 */

	@Autowired
	AppellationEventRepository app;
	
	@Autowired
	RelationEventRepository rel;
	
	@Autowired
	Element ele;
	
	@Override
	public HashMap<String, Integer> getStatistics() throws InvalidDataException {
		
		// I will fetch the statistics using a Neo4j connecter here
		
		
		HashMap<String, Integer> statisticsMap = new HashMap<String, Integer>();
		statisticsMap.put("nodes", ele.getElementCount());
		statisticsMap.put("relational",rel.getRelationEvenCount());
		statisticsMap.put("appellation", app.getAppellationEvenCount());
		
		return statisticsMap;
	}

}
