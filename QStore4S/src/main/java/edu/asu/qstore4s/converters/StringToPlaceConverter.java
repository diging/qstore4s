package edu.asu.qstore4s.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.IPlace;
import edu.asu.qstore4s.domain.elements.factory.IPlaceFactory;
/**
 * 
 * @author Bhargav Desai
 *
 */
@Service
public class StringToPlaceConverter implements Converter<String, IPlace> {
	@Autowired
	IPlaceFactory placeFactory;
	
	/**
	 * {@inheritDoc}
	 * The method convert String object to Place object.
	 * 
	 */
	
	@Override
	public IPlace convert(String sourceURI) {
	
	IPlace place = placeFactory.createPlace();	
		
		place.setSourceURI(sourceURI);
		return place;
		
	}
}
