package edu.asu.qstore4s.domain.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.IActor;
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
	public IActor createActor()
	{
		IActor actorObject = new Actor();
		return actorObject;
	}
	
	@Override
	public IActor createActor(String sourceUri)
	{
		IActor actorObject = new Actor();
		if(sourceUri==null)
		{
			actorObject.setSourceURI("");
		}
		else{
		actorObject.setSourceURI(sourceUri);}
		return actorObject;
	}

}
