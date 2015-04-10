package edu.asu.qstore4s.search.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.elements.ISearchTermPart;
import edu.asu.qstore4s.search.elements.factory.ISearchTermPartFactory;
import edu.asu.qstore4s.search.elements.impl.SearchTermPart;

@Service
public class SearchTermPartFactory implements ISearchTermPartFactory {
	
	@Override
	public ISearchTermPart createSearchTermPart()
	{
		ISearchTermPart termPartObject = new SearchTermPart();
		return termPartObject;
	}

}
