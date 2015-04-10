package edu.asu.qstore4s.search.elements.impl;

import edu.asu.qstore4s.search.elements.ISearchFormattedPointer;
/**
 * This file contains the definition of SearchFormattedPointer class.
 * @author Bhargav Desai
 *
 */


public class SearchFormattedPointer extends SearchElement implements ISearchFormattedPointer {
	
	private String formattedPointer;
	@Override
	public String getFormattedPointer(){
		return formattedPointer;
	}
	
	@Override
	public void setFormattedPointer(String formattedPointer){
		this.formattedPointer = formattedPointer;
	}

}
