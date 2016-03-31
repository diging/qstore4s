package edu.asu.qstore4s.search.elements.impl;

import edu.asu.qstore4s.search.elements.ISearchConcept;
/**
 * This file contains the definition of SearchConcept class.
 * @author Bhargav Desai
 *
 */


public class SearchConcept extends SearchElement implements ISearchConcept{

	private String sourceURI;

	@Override
	public String getSourceURI() {
		return sourceURI;
	}

	@Override
	public void setSourceURI(String sourceURI) {
		this.sourceURI = sourceURI;
	}	
	
}
