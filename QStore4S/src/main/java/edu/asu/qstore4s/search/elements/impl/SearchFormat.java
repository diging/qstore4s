package edu.asu.qstore4s.search.elements.impl;

import edu.asu.qstore4s.search.elements.ISearchFormat;

/**
 * This file contains the definition of SearchElement class.
 * @author Bhargav Desai
 *
 */


public class SearchFormat extends SearchElement implements ISearchFormat {
	
	private String format;

	
	@Override
	public String getFormat(){
		return format;
	}
	
	@Override
	public void setFormat(String format){
		this.format=format;
	}

}
