package edu.asu.qstore4s.search.elements.factory;

import edu.asu.qstore4s.search.elements.ISearchPlace;

public interface ISearchPlaceFactory {

	ISearchPlace createSearchPlace();

	ISearchPlace createSearchPlace(String sourceUri);
	
	

}
