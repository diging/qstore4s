package edu.asu.qstore4s.search.events;

import edu.asu.qstore4s.search.elements.ISearchActor;
import edu.asu.qstore4s.search.elements.ISearchRelation;

/**
 * 
 * @author Bhargav Desai
 *
 */

public interface ISearchRelationEvent extends ISearchCreationEvent{

	ISearchRelation getRelation();

	void setRelation(ISearchRelation relation);

	ISearchActor getRelationCreator();
	
	

	void setRelationCreator(ISearchActor actor);


}
