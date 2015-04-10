package edu.asu.qstore4s.search.elements.factory;

import edu.asu.qstore4s.search.elements.ISearchConcept;

public interface ISearchConceptFactory {

	ISearchConcept createSearchConcept();

	ISearchConcept createSearchConcept(String sourceUri);

}
