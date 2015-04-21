package edu.asu.qstore4s.domain.events.impl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.RelatedTo;

import edu.asu.qstore4s.domain.elements.impl.Actor;
import edu.asu.qstore4s.domain.elements.impl.Relation;

/**
 * This file contains the definition of RelationEvent class.
 *
 */
@XmlRootElement
public class RelationEvent extends CreationEvent {

	@Fetch
	@RelatedTo(type="hasRelation", direction=Direction.OUTGOING, elementClass=Relation.class)
	private Relation relation;
	
	@GraphProperty
	private Actor relation_creator;

	@XmlElement(type=Relation.class)
	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	@XmlElement(type=Actor.class)
	public Actor getRelationCreator() {
		return relation_creator;
	}

	public void setRelationCreator(Actor actor) {
		this.relation_creator = actor;
	}

}
