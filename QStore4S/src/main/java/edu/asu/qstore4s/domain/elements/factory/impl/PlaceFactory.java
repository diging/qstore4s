package edu.asu.qstore4s.domain.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.IPlace;
import edu.asu.qstore4s.domain.elements.factory.IPlaceFactory;
import edu.asu.qstore4s.domain.elements.impl.Place;

/**
 * This is the factory class for Place element. This is used to instantiate
 * Place class.
 * 
 * @author Veena Borannagowda
 * 
 */
@Service
public class PlaceFactory implements IPlaceFactory {
	@Override
	public IPlace createPlace() {
		IPlace placeObject = new Place();
		return placeObject;
	}

	@Override
	public IPlace createPlace(String sourceUri) {
		IPlace placeObject = new Place();
		if (sourceUri == null) {
			placeObject.setSourceURI("");
			} else {
			placeObject.setSourceURI(sourceUri);
		}
		return placeObject;
	}
}
