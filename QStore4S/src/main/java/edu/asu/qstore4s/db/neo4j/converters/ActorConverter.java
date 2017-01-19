package edu.asu.qstore4s.db.neo4j.converters;

import org.neo4j.ogm.typeconversion.AttributeConverter;

import edu.asu.qstore4s.domain.elements.impl.Actor;

public class ActorConverter implements AttributeConverter<Actor, String> {

    @Override
    public Actor toEntityAttribute(String arg0) {
        Actor actor = new Actor();

        actor.setSourceURI(arg0);
        return actor;
    }

    @Override
    public String toGraphProperty(Actor actor) {
        return actor.getSourceURI();
    }

}
