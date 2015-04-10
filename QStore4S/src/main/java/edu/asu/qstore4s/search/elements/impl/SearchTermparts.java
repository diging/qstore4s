package edu.asu.qstore4s.search.elements.impl;

import java.util.Set;

import edu.asu.qstore4s.search.elements.ISearchSourceReference;
import edu.asu.qstore4s.search.elements.ISearchTermPart;
import edu.asu.qstore4s.search.elements.ISearchTermParts;
/**
 * This file contains the definition of SearchTermParts class.
 * @author Bhargav Desai
 *
 */

public class SearchTermparts extends SearchElement implements ISearchTermParts {

	private Set<ISearchTermPart> termParts;
	
	private ISearchSourceReference source_reference;

	
	
@Override
public void setSearchTermParts(Set<ISearchTermPart> termPartList) {
	this.termParts=termPartList;
	
}

@Override
public ISearchSourceReference getReferencedSource() {
	return source_reference;
}

@Override
public void setReferencedSource(ISearchSourceReference reference) {
	this.source_reference = reference;
}



@Override
public Set<ISearchTermPart> getSearchTermParts()
{
	return termParts;
}

	
}
