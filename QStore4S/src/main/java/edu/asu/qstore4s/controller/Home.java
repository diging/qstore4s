package edu.asu.qstore4s.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.service.ISearch;
import edu.asu.qstore4s.service.IStatistics;

/**
 * Controller class to handle user requests.
 * 
 * @author Nikhil Aourpally.
 */
@Controller
public class Home {
	
	@Autowired
	private IStatistics statistics;
	
	@Autowired
    private ISearch search;
    
	/**
	 * An authenticated user is directed to the home page
	 * 
	 * @return 		Returned to the home page of Qstore.
	 */
	@RequestMapping(value = "auth/welcome", method = RequestMethod.GET)
	public String handleAuthUser(ModelMap model, Principal principal) throws InvalidDataException {
			HashMap <String, Integer> getStatistics = statistics.getStatistics();
			model.addAttribute("nodes", getStatistics.get(IStatistics.nodes));
			model.addAttribute("relational", getStatistics.get(IStatistics.relations));
			model.addAttribute("appellation", getStatistics.get(IStatistics.appellations));
			return "home";
	}
	/**
	 * 
	 * A user when performs a GET request on the auth/search URL, is redirected to the home page
	 * @param model
	 * @param principal
	 * @return
	 * @throws InvalidDataException
	 */
	
	@RequestMapping(value = "auth/search", method = RequestMethod.GET)
	public String conceptSearch( ModelMap model, Principal principal) throws InvalidDataException {
			return "home";
	} 
	/**
	 * @param concept
	 * @param model
	 * @param principal
	 * @return
	 * @throws InvalidDataException
	 * @throws IOException 
	 */
	
	@RequestMapping(value = "auth/searchsubmit", method = RequestMethod.POST)
	public String conceptSearch(@RequestParam(value = "concept", required = false)String concept, ModelMap model, Principal principal) throws InvalidDataException, IOException{
			JSONObject searchResults = search.getSearchResults(concept);
			return "search";
	}

	/**
	 * 
	 * @param model
	 * @param principal
	 * @return
	 * @throws InvalidDataException
	 */
	@RequestMapping(value = "auth/searchsubmit", method = RequestMethod.GET)
	public String getSearchRequest(ModelMap model, Principal principal) throws InvalidDataException {
			return "home";
	} 
}	

