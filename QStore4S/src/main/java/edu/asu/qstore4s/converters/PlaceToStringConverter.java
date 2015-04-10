package edu.asu.qstore4s.converters;

import org.springframework.core.convert.converter.Converter;

import edu.asu.qstore4s.domain.elements.IPlace;
/**
 * 
 * @author Bhargav Desai
 *
 */
public class PlaceToStringConverter implements Converter<IPlace, String> {

	
	/**
	 * {@inheritDoc}
	 * The method convert Place object to String.
	 * 
	 */
	@Override
	public String convert(IPlace place) {
		return place.getSourceURI();
	}

}
