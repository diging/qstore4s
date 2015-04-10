package edu.asu.qstore4s.search.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.elements.ISearchPlace;
import edu.asu.qstore4s.search.elements.factory.ISearchPlaceFactory;
import edu.asu.qstore4s.search.elements.impl.SearchPlace;

@Service
public class SearchPlaceFactory implements ISearchPlaceFactory {

	@Override
	public ISearchPlace createSearchPlace() {
		ISearchPlace placeObject = new SearchPlace();
		return placeObject;
	}

	@Override
	public ISearchPlace createSearchPlace(String sourceUri) {
		ISearchPlace placeObject = new SearchPlace();
		if (sourceUri == null) {
			placeObject.setSourceURI("");
			} else {
			placeObject.setSourceURI(sourceUri);
		}
		return placeObject;
	}
}
