package edu.asu.qstore4s.db.neo4j.converters;

import org.neo4j.ogm.typeconversion.AttributeConverter;

import edu.asu.qstore4s.domain.elements.impl.SourceReference;

public class SourceReferenceConverter implements AttributeConverter<SourceReference, String>  {

    @Override
    public SourceReference toEntityAttribute(String arg0) {
        SourceReference reference = new SourceReference();
        reference.setSourceURI(arg0);
        return reference;
    }

    @Override
    public String toGraphProperty(SourceReference sourceReference) {
        return sourceReference.getSourceURI();
    }

}
