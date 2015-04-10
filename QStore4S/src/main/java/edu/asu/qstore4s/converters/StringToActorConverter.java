package edu.asu.qstore4s.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.IActor;
import edu.asu.qstore4s.domain.elements.factory.IActorFactory;

/**
 * 
 * @author Bhargav Desai
 *
 */

@Service
public class StringToActorConverter implements Converter<String, IActor> {
	@Autowired
	IActorFactory actorFactory;
	
	
	
	/**
	 * {@inheritDoc}
	 * The method convert String object to Actor object.
	 * 
	 */
	@Override
	public IActor convert(String sourceURI) {
	
	IActor actor = actorFactory.createActor();	
		
		actor.setSourceURI(sourceURI);
		return actor;
		
	}

}
