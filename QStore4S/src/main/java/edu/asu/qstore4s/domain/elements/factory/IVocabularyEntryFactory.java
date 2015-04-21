package edu.asu.qstore4s.domain.elements.factory;

import edu.asu.qstore4s.domain.elements.impl.VocabularyEntry;


/**
 * This is the interface class for VocabularyEntryFactory class
 * which has the following methods:
 * createVocabularyEntry()
 * @author Veena Borannagowda
 *
 */
public interface IVocabularyEntryFactory {

	VocabularyEntry createVocabularyEntry();

	VocabularyEntry createVocabularyEntry(String sourceUri);

}
