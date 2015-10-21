package edu.asu.qstore4s.service;

import java.util.HashMap;
import java.util.List;

import edu.asu.qstore4s.exception.InvalidDataException;

/**
 * 
 * @author Nikhil Aourpally.
 */
public interface ISearch {
	public final String id = "id";
	public final String URI = "URI";
	public final String description = "Description";

	public List<HashMap<String,String>> getSearchResults(String searchTerm) throws InvalidDataException;
}
