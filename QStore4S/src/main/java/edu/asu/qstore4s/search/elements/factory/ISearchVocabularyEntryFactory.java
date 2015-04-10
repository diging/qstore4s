package edu.asu.qstore4s.search.elements.factory;

import edu.asu.qstore4s.search.elements.ISearchVocabularyEntry;

public interface ISearchVocabularyEntryFactory {

	ISearchVocabularyEntry createSearchVocabularyEntry();

	ISearchVocabularyEntry createSearchVocabularyEntry(String sourceUri);

}
