package edu.asu.qstore4s.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.factory.IActorFactory;
import edu.asu.qstore4s.domain.elements.impl.Actor;

/**
 * 
 * @author Bhargav Desai
 *
 */

@Service
public class StringToActorConverter implements Converter<String, Actor> {
	@Autowired
	IActorFactory actorFactory;
	
	
	
	/**
	 * {@inheritDoc}
	 * The method convert String object to Actor object.
	 * 
	 */
	@Override
	public Actor convert(String sourceURI) {
	
		Actor actor = actorFactory.createActor();	
		actor.setSourceURI(sourceURI);
		return actor;
		
	}

}
