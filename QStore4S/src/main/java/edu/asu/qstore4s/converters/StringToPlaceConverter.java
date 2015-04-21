package edu.asu.qstore4s.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.factory.IPlaceFactory;
import edu.asu.qstore4s.domain.elements.impl.Place;
/**
 * 
 * @author Bhargav Desai
 *
 */
@Service
public class StringToPlaceConverter implements Converter<String, Place> {
	@Autowired
	IPlaceFactory placeFactory;
	
	/**
	 * {@inheritDoc}
	 * The method convert String object to Place object.
	 * 
	 */
	
	@Override
	public Place convert(String sourceURI) {
	
		Place place = placeFactory.createPlace();	
		
		place.setSourceURI(sourceURI);
		return place;
		
	}
}
