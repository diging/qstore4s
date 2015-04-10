package edu.asu.qstore4s.domain.events.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.events.IRelationEvent;
import edu.asu.qstore4s.domain.events.factory.IRelationEventFactory;
import edu.asu.qstore4s.domain.events.impl.RelationEvent;

/**
 * This is the factory class for RelationEvent element. 
 * This is used to instantiate RelationEvent class.
 * @author Veena Borannagowda
 *
 */

@Service
public class RelationEventFactory implements IRelationEventFactory {
	@Override
	public IRelationEvent createRelationEvent()
	{
		IRelationEvent relationEventObject = new RelationEvent();
		return relationEventObject;
	}
}
