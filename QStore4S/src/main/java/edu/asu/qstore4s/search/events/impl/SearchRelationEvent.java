package edu.asu.qstore4s.search.events.impl;

import edu.asu.qstore4s.search.elements.ISearchActor;
import edu.asu.qstore4s.search.elements.ISearchRelation;
import edu.asu.qstore4s.search.events.ISearchRelationEvent;

/**
 * 
 * @author Bhargav Desai
 *
 */

public class SearchRelationEvent extends SearchCreationEvent implements ISearchRelationEvent  {

	
	private ISearchRelation relation;
	
	private ISearchActor relation_creator;

	@Override
	public ISearchRelation getRelation() {
		return relation;
	}

	@Override
	public void setRelation(ISearchRelation relation) {
		this.relation = relation;
	}

	@Override
	public ISearchActor getRelationCreator() {
		return relation_creator;
	}

	@Override
	public void setRelationCreator(ISearchActor actor) {
		this.relation_creator = actor;
	}


	
	
}
