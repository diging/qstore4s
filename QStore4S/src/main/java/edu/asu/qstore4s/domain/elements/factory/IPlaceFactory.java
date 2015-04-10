package edu.asu.qstore4s.domain.elements.factory;

import edu.asu.qstore4s.domain.elements.IPlace;

/**
 * This is the interface class for PlaceFactory class
 * which has the following methods:
 * createPlace()
 * @author Veena Borannagowda
 *
 */
public interface IPlaceFactory {

	IPlace createPlace();
	IPlace createPlace(String sourceUri);

}
