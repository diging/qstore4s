package edu.asu.qstore4s.search.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.elements.factory.ISearchObjectFactory;
import edu.asu.qstore4s.search.elements.impl.SearchObject;

@Service
public class SearchObjectFactory implements ISearchObjectFactory {

	@Override
	public SearchObject createSearchObject(){
		SearchObject object = new SearchObject();
		return object;
	}

}
