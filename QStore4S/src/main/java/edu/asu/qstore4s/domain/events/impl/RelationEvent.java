package edu.asu.qstore4s.domain.events.impl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import edu.asu.qstore4s.db.neo4j.converters.ActorConverter;
import edu.asu.qstore4s.domain.elements.impl.Actor;
import edu.asu.qstore4s.domain.elements.impl.Relation;

/**
 * This file contains the definition of RelationEvent class.
 *
 */
@XmlRootElement
public class RelationEvent extends CreationEvent {

    @Relationship(type = "hasRelation", direction = Relationship.OUTGOING)
    private Relation relation;

    @Property(name = "relation_creator")
    @Convert(ActorConverter.class)
    private Actor relation_creator;

    @XmlElement(type = Relation.class)
    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    @XmlElement(type = Actor.class)
    public Actor getRelationCreator() {
        return relation_creator;
    }

    public void setRelationCreator(Actor actor) {
        this.relation_creator = actor;
    }

}
