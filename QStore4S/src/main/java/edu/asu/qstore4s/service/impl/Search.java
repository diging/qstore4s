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
		//Retrieve all the Appellations from the Data store related to the concept(searchTerm) and return them as a List of HashMaps
		JSONObject jo = new JSONObject();
		jo.put(id,"1");
		jo.put(URI,"www.dummy.com");
		jo.put(description,"This is a dummy decription");
		JSONArray ja = new JSONArray();
		ja.add(jo);
		JSONObject result = new JSONObject();
		result.put("nodes", ja);
		return result;
	}
}
