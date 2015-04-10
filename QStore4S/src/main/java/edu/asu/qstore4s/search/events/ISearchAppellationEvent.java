package edu.asu.qstore4s.search.events;

import edu.asu.qstore4s.search.elements.ISearchTerm;

/**
 * 
 * @author Bhargav Desai
 *
 */

public interface ISearchAppellationEvent extends ISearchCreationEvent{

	ISearchTerm getTerm();

	void setTerm(ISearchTerm term);


}
