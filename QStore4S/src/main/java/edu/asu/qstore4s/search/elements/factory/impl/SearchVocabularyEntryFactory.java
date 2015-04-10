package edu.asu.qstore4s.search.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.elements.ISearchVocabularyEntry;
import edu.asu.qstore4s.search.elements.factory.ISearchVocabularyEntryFactory;
import edu.asu.qstore4s.search.elements.impl.SearchVocabularyEntry;
@Service
public class SearchVocabularyEntryFactory implements ISearchVocabularyEntryFactory {

	@Override
	public ISearchVocabularyEntry createSearchVocabularyEntry()
	{
		ISearchVocabularyEntry vocabularyEntryObject = new SearchVocabularyEntry();
		return vocabularyEntryObject;
	}
	
	@Override
	public ISearchVocabularyEntry createSearchVocabularyEntry(String sourceUri)
	{
		ISearchVocabularyEntry vocabularyEntryObject = new SearchVocabularyEntry();
		if(sourceUri==null){
			vocabularyEntryObject.setSourceURI("");
		}
		else{
		vocabularyEntryObject.setSourceURI(sourceUri);}
		return vocabularyEntryObject;
	}
}
