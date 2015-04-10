package edu.asu.qstore4s.search.elements.impl;

import edu.asu.qstore4s.search.elements.ISearchSourceReference;
/**
 * This file contains the definition of SearchSourceReference class.
 * @author Bhargav Desai
 *
 */

public class SearchSourceReference extends SearchElement implements ISearchSourceReference {

	
private String sourceURI;
	
	public SearchSourceReference() {

	sourceURI="";
	}
	
	@Override
	public String getSourceURI() {
		return sourceURI;
	}

	@Override
	public void setSourceURI(String uri) {
		this.sourceURI = uri;
	}

}
