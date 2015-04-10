package edu.asu.qstore4s.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.IVocabularyEntry;
import edu.asu.qstore4s.domain.elements.factory.IVocabularyEntryFactory;
/**
 * 
 * @author Bhargav Desai
 *
 */
@Service
public class StringToVocabularyEntryConverter implements Converter<String, IVocabularyEntry> {
	@Autowired
	IVocabularyEntryFactory vocabularyEntryFactory;
	/**
	 * {@inheritDoc}
	 * The method convert vocabularyEntry object to String object.
	 * 
	 */
	@Override
	public IVocabularyEntry convert(String source)
	{
		IVocabularyEntry vocabularyEntry = vocabularyEntryFactory.createVocabularyEntry(source);
		return vocabularyEntry;
	}

}
