package edu.asu.qstore4s.domain.events.impl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.RelatedTo;

import edu.asu.qstore4s.domain.elements.IActor;
import edu.asu.qstore4s.domain.elements.IRelation;
import edu.asu.qstore4s.domain.elements.impl.Actor;
import edu.asu.qstore4s.domain.elements.impl.Relation;
import edu.asu.qstore4s.domain.events.IRelationEvent;

/**
 * This file contains the definition of RelationEvent class.
 *
 */
@XmlRootElement

public class RelationEvent extends CreationEvent implements IRelationEvent {

	@Fetch
	@RelatedTo(type="hasRelation", direction=Direction.OUTGOING, elementClass=Relation.class)
	private IRelation relation;
	
	@GraphProperty
	private IActor relation_creator;

	@Override
	@XmlElement(type=Relation.class)
	public IRelation getRelation() {
		return relation;
	}

	@Override
	public void setRelation(IRelation relation) {
		this.relation = relation;
	}

	@Override
	@XmlElement(type=Actor.class)
	public IActor getRelationCreator() {
		return relation_creator;
	}

	@Override
	public void setRelationCreator(IActor actor) {
		this.relation_creator = actor;
	}

	public static class Adapter extends XmlAdapter<RelationEvent,IRelationEvent> {
		public IRelationEvent unmarshal(RelationEvent v) { return v; }
		public RelationEvent marshal(IRelationEvent v) { return (RelationEvent)v; }
	}
}
