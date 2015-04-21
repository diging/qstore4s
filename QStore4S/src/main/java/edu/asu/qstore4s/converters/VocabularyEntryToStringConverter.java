package edu.asu.qstore4s.converters;

import org.springframework.core.convert.converter.Converter;

import edu.asu.qstore4s.domain.elements.impl.VocabularyEntry;
/**
 * 
 * @author Bhargav Desai
 *
 */
public class VocabularyEntryToStringConverter implements Converter<VocabularyEntry, String> {
	
	
	/**
	 * {@inheritDoc}
	 * The method convert String object to vocabularyEntry object.
	 * 
	 */
	@Override
	public String convert(VocabularyEntry vocabularyEntry)
	{
		return vocabularyEntry.getSourceURI();
	}

}
