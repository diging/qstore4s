package edu.asu.qstore4s.service;

import java.util.HashMap;
import edu.asu.qstore4s.exception.InvalidDataException;

/**
 * Interface to get statistics from QStore.
 * 
 * @author Nikhil Aourpally.
 */
public interface IStatistics {
    public final String NODES = "nodes";
    public final String RELATIONS = "relations";
    public final String APPELLATIONS = "appellations";

    HashMap<String, Integer> getStatistics() throws InvalidDataException;

}
