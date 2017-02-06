package edu.asu.qstore4s.db.neo4j.converters;

import org.neo4j.ogm.typeconversion.AttributeConverter;

import edu.asu.qstore4s.domain.elements.impl.VocabularyEntry;

public class VocabularyEntryConverter implements AttributeConverter<VocabularyEntry, String> {

    @Override
    public VocabularyEntry toEntityAttribute(String arg0) {
        VocabularyEntry vocabularyEntryObject = new VocabularyEntry();
        vocabularyEntryObject.setSourceURI(arg0 == null ? "" : arg0);
        return vocabularyEntryObject;
    }

    @Override
    public String toGraphProperty(VocabularyEntry vocabularyEntry) {
        return vocabularyEntry.getSourceURI();
    }

}
