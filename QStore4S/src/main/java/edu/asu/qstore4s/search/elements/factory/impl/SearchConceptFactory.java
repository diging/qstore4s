package edu.asu.qstore4s.search.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.elements.ISearchConcept;
import edu.asu.qstore4s.search.elements.factory.ISearchConceptFactory;
import edu.asu.qstore4s.search.elements.impl.SearchConcept;

@Service
public class SearchConceptFactory implements ISearchConceptFactory {

	@Override
	public ISearchConcept createSearchConcept()
	{
		ISearchConcept conceptObject = new SearchConcept();
		return conceptObject;
	}
	
	@Override
	public ISearchConcept createSearchConcept(String sourceUri)
	{
		ISearchConcept conceptObject = new SearchConcept();
		conceptObject.setSourceURI(sourceUri == null ? "" : sourceUri);
		return conceptObject;
	}
}
