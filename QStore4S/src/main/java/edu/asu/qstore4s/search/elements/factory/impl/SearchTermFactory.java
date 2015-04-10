package edu.asu.qstore4s.search.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.elements.ISearchTerm;
import edu.asu.qstore4s.search.elements.factory.ISearchTermFactory;
import edu.asu.qstore4s.search.elements.impl.SearchTerm;

@Service
public class SearchTermFactory implements ISearchTermFactory{

	@Override
	public ISearchTerm createSearchTerm()
	{
		ISearchTerm termObject = new SearchTerm();
		return termObject;
	}
}
