package edu.asu.qstore4s.search.elements;


import edu.asu.qstore4s.search.events.ISearchAppellationEvent;
import edu.asu.qstore4s.search.events.ISearchCreationEvent;

/**
 * 
 * @author Bhargav Desai
 *
 */

public interface ISearchRelation extends ISearchElement {

	void setSubject(ISearchCreationEvent subject);

	void setObject(ISearchCreationEvent object);

	ISearchAppellationEvent getPredicate();

	void setPredicate(ISearchAppellationEvent predicate);

	ISearchSourceReference getSourceReference();

	void setSourceReference(ISearchSourceReference reference);
	

	ISearchCreationEvent getObject();

	ISearchCreationEvent getSubject();

	
	
}
