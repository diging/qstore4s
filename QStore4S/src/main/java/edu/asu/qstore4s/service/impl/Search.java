package edu.asu.qstore4s.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	public List<HashMap<String,String>> getSearchResults(String searchTerm) throws InvalidDataException{
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> eventA = new HashMap<String, String>();
		eventA.put(id,"1");
		eventA.put(URI,"www.dummy.com");
		eventA.put(description,"This is a dummy decription");
		result.add(eventA);
		return result;
	}
}
