package edu.asu.qstore4s.domain.elements.factory;

import edu.asu.qstore4s.domain.elements.IActor;

/**
 * This is the interface class for ActoryFactory class
 * which has the following methods:
 * createActor()
 * @author Veena Borannagowda
 *
 */
public interface IActorFactory {

	IActor createActor();

	IActor createActor(String sourceUri);
	
}
