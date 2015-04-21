package edu.asu.qstore4s.domain.events.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.events.factory.IAppellationEventFactory;
import edu.asu.qstore4s.domain.events.impl.AppellationEvent;

/**
 * This is the factory class for AppellationEvent element. 
 * This is used to instantiate AppellationEvent class.
 * @author Veena Borannagowda
 *
 */

@Service
public class AppellationEventFactory implements IAppellationEventFactory {
@Override
	public AppellationEvent createAppellationEvent()
	{
		return new AppellationEvent();
	}
}
