package edu.asu.qstore4s.converters;

import org.springframework.core.convert.converter.Converter;

import edu.asu.qstore4s.domain.elements.impl.Place;
/**
 * 
 * @author Bhargav Desai
 *
 */
public class PlaceToStringConverter implements Converter<Place, String> {

	
	/**
	 * {@inheritDoc}
	 * The method convert Place object to String.
	 * 
	 */
	@Override
	public String convert(Place place) {
		return place.getSourceURI();
	}

}
