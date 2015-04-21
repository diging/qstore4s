package edu.asu.qstore4s.domain.events.factory;

import edu.asu.qstore4s.domain.events.impl.RelationEvent;

/**
 * This is the interface class for RelationEventFactory class
 * which has the following methods:
 * createRelationEvent()
 * @author Veena Borannagowda
 *
 */
public interface IRelationEventFactory {

	RelationEvent createRelationEvent();

}
