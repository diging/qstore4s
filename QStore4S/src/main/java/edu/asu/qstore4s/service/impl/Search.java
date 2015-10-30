package edu.asu.qstore4s.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.service.ISearch;

@Service
public class Search implements ISearch{

	/**
	 * The method returns the list of appellation events related to a concept
	 *
	 * @return
	 * @throws InvalidDataException 
	 */
	
	@Override
	public JSONObject getSearchResults(String searchTerm) throws InvalidDataException{
		//Retrieve all the Appellation from the Data store related to the concept(searchTerm) and return them as a List of HashMaps
		JSONObject jo1 = new JSONObject();
		JSONArray ja1 = new JSONArray();
		jo1.put(id,"1");
		jo1.put(URI,"www.dummySource.com");
		jo1.put(description,"This is a dummy source");
		ja1.add(jo1);
		
		JSONObject jo2 = new JSONObject();
		jo2.put(id,"2");
		jo2.put(URI,"www.dummyDestination.com");
		jo2.put(description,"This is a dummy destination");
		ja1.add(jo2);

		JSONObject result = new JSONObject();
		result.put("nodes", ja1);
		
		JSONArray ja2 = new JSONArray();
		JSONObject jo3 = new JSONObject();
		jo3.put("source","1");
		jo3.put("target","2");
		jo3.put("value","2");
		ja2.add(jo3);
		
		result.put("links", ja2);
		return result;
	}
}
