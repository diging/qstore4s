package edu.asu.qstore4s.domain.events.factory;

import edu.asu.qstore4s.domain.events.impl.AppellationEvent;

/**
 * This is the interface class for AppellationEventFactory class
 * which has the following methods:
 * createAppellationEvent()
 * @author Veena Borannagowda
 *
 */
public interface IAppellationEventFactory {

	AppellationEvent createAppellationEvent();

}
