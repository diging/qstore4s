package edu.asu.qstore4s.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value = "auth/search", method = RequestMethod.POST)
	public String conceptSearch(ModelMap model, Principal principal) throws InvalidDataException {
			List<HashMap<String, String>> searchResults = search.getSearchResults("test");
			return "search";
	} 	
}	

