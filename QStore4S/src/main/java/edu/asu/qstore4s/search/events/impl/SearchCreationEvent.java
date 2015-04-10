package edu.asu.qstore4s.search.events.impl;

import edu.asu.qstore4s.search.elements.ISearchActor;
import edu.asu.qstore4s.search.elements.ISearchSourceReference;
import edu.asu.qstore4s.search.elements.impl.SearchElement;
import edu.asu.qstore4s.search.events.ISearchCreationEvent;


/**
 * 
 * @author Bhargav Desai
 *
 */

public class SearchCreationEvent extends SearchElement implements ISearchCreationEvent {


	private ISearchSourceReference source_reference;
	
	
	
	private ISearchActor interpretation_creator;
	
	
	@Override
	public ISearchSourceReference getSourceReference() {
		return source_reference;
	}

	@Override
	public void setSourceReference(ISearchSourceReference reference) {
		this.source_reference = reference;
	}
	@Override
	public ISearchActor getInterpretationCreator() {
		return interpretation_creator;
	}

	@Override
	public void setInterpretationCreator(ISearchActor interpretationCreator) {
		this.interpretation_creator = interpretationCreator;
	}

	
	
}
