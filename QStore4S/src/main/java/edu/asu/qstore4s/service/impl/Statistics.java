package edu.asu.qstore4s.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.exception.InvalidDataException;
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
	
	@Override
	public HashMap<String, Integer> getStatistics() throws InvalidDataException {
		HashMap<String, Integer> statisticsMap = new HashMap<String, Integer>();
		statisticsMap.put(nodes, 10);
		statisticsMap.put(relationas,20);
		statisticsMap.put(appellations, 100);
		return statisticsMap;
	}

}
