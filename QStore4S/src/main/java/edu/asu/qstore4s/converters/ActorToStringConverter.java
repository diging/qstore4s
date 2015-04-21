package edu.asu.qstore4s.converters;

import org.springframework.core.convert.converter.Converter;

import edu.asu.qstore4s.domain.elements.impl.Actor;

/**
 * 
 * @author Bhargav Desai
 *
 */

public class ActorToStringConverter implements Converter<Actor, String> {
/**
 * {@inheritDoc}
 * The method convert Actor object to String.
 * 
 */
	@Override
	public String convert(Actor actor) {
		return actor.getSourceURI();
	}

	
}
