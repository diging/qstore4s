package edu.asu.qstore4s.domain.elements.factory;

import edu.asu.qstore4s.domain.elements.impl.Place;


/**
 * This is the interface class for PlaceFactory class
 * which has the following methods:
 * createPlace()
 * @author Veena Borannagowda
 *
 */
public interface IPlaceFactory {

	Place createPlace();
	Place createPlace(String sourceUri);

}
