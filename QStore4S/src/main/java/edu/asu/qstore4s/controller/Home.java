package edu.asu.qstore4s.controller;

import java.security.Principal;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.service.IStatistics;

/**
 * Controller class to handle user requests.
 * 
 * @author Nikhil Aourpally.
 */

@Controller
public class Home {
	
	@Autowired
	IStatistics statistics;
	
	/**
	 * A valid authenticated user is redirected to the home page.
	 * 
	 * @return 		Returned to the home page of Qstore.
	 */
	
	@RequestMapping(value = "auth/welcome", method = RequestMethod.GET)
	public String validUserHandle(ModelMap model, Principal principal) {
		try {
			
			HashMap <String, Integer> getStatistics = statistics.getStatistics();
			model.addAttribute("nodes", getStatistics.get("nodes"));
			model.addAttribute("relational", getStatistics.get("relational"));
			model.addAttribute("appellation", getStatistics.get("appellation"));
			
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		return "home";
	}	
}
