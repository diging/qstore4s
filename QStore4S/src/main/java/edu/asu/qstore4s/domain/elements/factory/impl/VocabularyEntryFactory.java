package edu.asu.qstore4s.domain.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.IVocabularyEntry;
import edu.asu.qstore4s.domain.elements.factory.IVocabularyEntryFactory;
import edu.asu.qstore4s.domain.elements.impl.VocabularyEntry;

/**
 * This is the factory class for VocabularyEntry element. 
 * This is used to instantiate VocabularyEntry class.
 * @author Veena Borannagowda
 *
 */
@Service
public class VocabularyEntryFactory implements IVocabularyEntryFactory {
	@Override
	public IVocabularyEntry createVocabularyEntry()
	{
		IVocabularyEntry vocabularyEntryObject = new VocabularyEntry();
		return vocabularyEntryObject;
	}
	
	@Override
	public IVocabularyEntry createVocabularyEntry(String sourceUri)
	{
		IVocabularyEntry vocabularyEntryObject = new VocabularyEntry();
		if(sourceUri==null){
			vocabularyEntryObject.setSourceURI("");
		}
		else{
		vocabularyEntryObject.setSourceURI(sourceUri);}
		return vocabularyEntryObject;
	}
}
