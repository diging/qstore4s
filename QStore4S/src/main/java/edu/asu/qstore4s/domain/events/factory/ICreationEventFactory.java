package edu.asu.qstore4s.domain.events.factory;

import edu.asu.qstore4s.domain.events.impl.CreationEvent;

/**
 * This is the interface class for CreationEventFactory class
 * which has the following methods:
 * createCreationEvent()
 * @author Veena Borannagowda
 *
 */
public interface ICreationEventFactory {

	CreationEvent createCreationEvent();

}
