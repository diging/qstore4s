package edu.asu.qstore4s.domain.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.factory.IRelationFactory;
import edu.asu.qstore4s.domain.elements.impl.Relation;

/**
 * This is the factory class for Relation element. 
 * This is used to instantiate Relation class.
 * @author Veena Borannagowda
 *
 */
@Service
public class RelationFactory implements IRelationFactory {
	public Relation createRelation()
	{
		return new Relation();
	}
}
