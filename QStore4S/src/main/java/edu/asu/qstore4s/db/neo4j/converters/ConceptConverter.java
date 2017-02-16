package edu.asu.qstore4s.db.neo4j.converters;

import org.neo4j.ogm.typeconversion.AttributeConverter;

import edu.asu.qstore4s.domain.elements.impl.Concept;

public class ConceptConverter implements AttributeConverter<Concept, String> {

    @Override
    public Concept toEntityAttribute(String sourceUri) {
        Concept conceptObject = new Concept();
        conceptObject.setSourceURI(sourceUri == null ? "" : sourceUri);
        return conceptObject;
    }

    @Override
    public String toGraphProperty(Concept concept) {
        return concept.getSourceURI();
    }

}
