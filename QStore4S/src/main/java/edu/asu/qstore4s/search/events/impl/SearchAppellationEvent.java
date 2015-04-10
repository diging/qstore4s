package edu.asu.qstore4s.search.events.impl;

import edu.asu.qstore4s.search.elements.ISearchTerm;
import edu.asu.qstore4s.search.events.ISearchAppellationEvent;

/**
 * 
 * @author Bhargav Desai
 *
 */


public class SearchAppellationEvent extends SearchCreationEvent implements ISearchAppellationEvent {

private ISearchTerm term;
	
	@Override
	public ISearchTerm getTerm() {
		return term;
	}

	@Override
	public void setTerm(ISearchTerm term) {
		this.term = term;
	}


	
	
}
