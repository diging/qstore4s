package edu.asu.qstore4s.converters;

import org.springframework.core.convert.converter.Converter;

import edu.asu.qstore4s.domain.elements.IVocabularyEntry;
/**
 * 
 * @author Bhargav Desai
 *
 */
public class VocabularyEntryToStringConverter implements Converter<IVocabularyEntry, String> {
	
	
	/**
	 * {@inheritDoc}
	 * The method convert String object to vocabularyEntry object.
	 * 
	 */
	@Override
	public String convert(IVocabularyEntry vocabularyEntry)
	{
		return vocabularyEntry.getSourceURI();
	}

}
