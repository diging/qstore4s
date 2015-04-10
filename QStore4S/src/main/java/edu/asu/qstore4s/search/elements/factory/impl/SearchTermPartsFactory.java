package edu.asu.qstore4s.search.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.elements.ISearchTermParts;
import edu.asu.qstore4s.search.elements.factory.ISearchTermPartsFactory;
import edu.asu.qstore4s.search.elements.impl.SearchTermparts;

@Service
public class SearchTermPartsFactory implements ISearchTermPartsFactory{

	@Override
	public ISearchTermParts createSearchTermParts()
	{
		ISearchTermParts termPartsObject = new SearchTermparts();
		return termPartsObject;
	}
}
