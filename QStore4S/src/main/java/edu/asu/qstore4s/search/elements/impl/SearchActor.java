package edu.asu.qstore4s.search.elements.impl;

import edu.asu.qstore4s.search.elements.ISearchActor;
/**
 * This file contains the definition of SearchActor class.
 * @author Bhargav Desai
 *
 */


public class SearchActor extends SearchConcept implements ISearchActor{
	
	private String searchType;

	@Override
	public String getSearchType() {
		return searchType;
	}
	@Override
	public void setSearchType(String searchType) {
		this.searchType = searchType;
		
		
	}
	
	
	public SearchActor() {
		
	searchType="equals";
	}

}
