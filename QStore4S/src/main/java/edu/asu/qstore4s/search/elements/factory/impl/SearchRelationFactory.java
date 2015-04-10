package edu.asu.qstore4s.search.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.elements.ISearchRelation;
import edu.asu.qstore4s.search.elements.factory.ISearchRelationFactory;
import edu.asu.qstore4s.search.elements.impl.SearchRelation;

@Service
public class SearchRelationFactory implements ISearchRelationFactory {

	@Override
	public ISearchRelation createSearchRelation()
	{
		ISearchRelation relationObject = new SearchRelation();
		return relationObject;
	}
}
