package edu.asu.qstore4s.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.factory.IVocabularyEntryFactory;
import edu.asu.qstore4s.domain.elements.impl.VocabularyEntry;
/**
 * 
 * @author Bhargav Desai
 *
 */
@Service
public class StringToVocabularyEntryConverter implements Converter<String, VocabularyEntry> {
	@Autowired
	IVocabularyEntryFactory vocabularyEntryFactory;
	/**
	 * {@inheritDoc}
	 * The method convert vocabularyEntry object to String object.
	 * 
	 */
	@Override
	public VocabularyEntry convert(String source)
	{
		return vocabularyEntryFactory.createVocabularyEntry(source);
	}

}
