package edu.asu.qstore4s.domain.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.factory.IActorFactory;
import edu.asu.qstore4s.domain.elements.impl.Actor;

/**
 * This is the factory class for Actor element. 
 * This is used to instantiate Actor class.
 * @author Veena Borannagowda
 *
 */

@Service
public class ActorFactory implements IActorFactory {
	@Override
	public Actor createActor()
	{
		return new Actor();
	}
	
	@Override
	public Actor createActor(String sourceUri)
	{
		Actor actorObject = new Actor();
		if(sourceUri==null)
		{
			actorObject.setSourceURI("");
		}
		else{
		actorObject.setSourceURI(sourceUri);}
		return actorObject;
	}

}
