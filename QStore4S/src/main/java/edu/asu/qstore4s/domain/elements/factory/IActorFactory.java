package edu.asu.qstore4s.domain.elements.factory;

import edu.asu.qstore4s.domain.elements.impl.Actor;

/**
 * This is the interface class for ActoryFactory class
 * which has the following methods:
 * createActor()
 * @author Veena Borannagowda
 *
 */
public interface IActorFactory {

	Actor createActor();

	Actor createActor(String sourceUri);
	
}
