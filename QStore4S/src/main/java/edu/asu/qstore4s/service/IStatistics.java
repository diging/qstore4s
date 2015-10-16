package edu.asu.qstore4s.service;

import java.util.HashMap;
import edu.asu.qstore4s.exception.InvalidDataException;

/**
 * 
 * @author Nikhil Aourpally.
 */
public interface IStatistics {
	public final String nodes = "nodes";
	public final String relationas = "relations";
	public final String appellations = "appellations";
	
	HashMap<String, Integer> getStatistics () throws InvalidDataException;
	
}
