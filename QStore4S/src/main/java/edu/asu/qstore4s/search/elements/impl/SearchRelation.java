package edu.asu.qstore4s.search.elements.impl;

import edu.asu.qstore4s.search.elements.ISearchRelation;
import edu.asu.qstore4s.search.elements.ISearchSourceReference;
import edu.asu.qstore4s.search.events.ISearchAppellationEvent;
import edu.asu.qstore4s.search.events.ISearchCreationEvent;
/**
 * This file contains the definition of SearchRelation class.
 * @author Bhargav Desai
 *
 */


public class SearchRelation extends SearchElement implements ISearchRelation{
	
	private ISearchCreationEvent subject;
	
	private ISearchCreationEvent object;
	
	private ISearchAppellationEvent predicate;
	
	private ISearchSourceReference source_reference;
	
	@Override
	public ISearchCreationEvent getSubject() {
		return subject;
	}

	@Override
	public void setSubject(ISearchCreationEvent subject) {
		this.subject = subject;
	}
	@Override
	public ISearchCreationEvent getObject() {
		return object;
	}

	@Override
	public void setObject(ISearchCreationEvent object) {
		this.object = object;
	}

	@Override
	public ISearchAppellationEvent getPredicate() {
		return predicate;
	}

	@Override
	public void setPredicate(ISearchAppellationEvent predicate) {
		this.predicate = predicate;
	}

	@Override
	public ISearchSourceReference getSourceReference() {
		return source_reference;
	}

	@Override
	public void setSourceReference(ISearchSourceReference reference) {
		this.source_reference = reference;
	}

	
	
	
}
