package edu.asu.qstore4s.converters;

import org.springframework.core.convert.converter.Converter;

import edu.asu.qstore4s.domain.elements.IActor;

/**
 * 
 * @author Bhargav Desai
 *
 */

public class ActorToStringConverter implements Converter<IActor, String> {
/**
 * {@inheritDoc}
 * The method convert Actor object to String.
 * 
 */
	@Override
	public String convert(IActor actor) {
		return actor.getSourceURI();
	}

	
}
