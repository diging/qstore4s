package edu.asu.qstore4s.search.elements.factory;

import edu.asu.qstore4s.search.elements.ISearchSourceReference;

public interface ISearchSourceReferenceFactory {

	ISearchSourceReference createSearchSourceReference();

	ISearchSourceReference createSearchSourceReference(String sourceUri);

}
