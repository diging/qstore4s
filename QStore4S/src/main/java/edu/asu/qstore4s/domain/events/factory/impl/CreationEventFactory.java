package edu.asu.qstore4s.domain.events.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.events.factory.ICreationEventFactory;
import edu.asu.qstore4s.domain.events.impl.CreationEvent;

/**
 * This is the factory class for CreationEvent element. 
 * This is used to instantiate CreationEvent class.
 * @author Veena Borannagowda
 *
 */

@Service
public class CreationEventFactory implements ICreationEventFactory {
@Override
	public CreationEvent createCreationEvent()
	{
		return new CreationEvent();
	}
}
