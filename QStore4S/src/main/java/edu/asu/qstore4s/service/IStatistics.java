package edu.asu.qstore4s.service;

import java.util.HashMap;
import org.springframework.stereotype.Service;
import edu.asu.qstore4s.exception.InvalidDataException;

/**
 * 
 * @author Nikhil Aourpally.
 */

@Service
public interface IStatistics {
	
	HashMap<String, Integer> getStatistics () throws InvalidDataException;
	
}
