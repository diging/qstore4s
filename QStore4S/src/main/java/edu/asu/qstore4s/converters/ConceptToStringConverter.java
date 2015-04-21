package edu.asu.qstore4s.converters;

import org.springframework.core.convert.converter.Converter;

import edu.asu.qstore4s.domain.elements.impl.Concept;
/**
 * 
 * @author Bhargav Desai
 *
 */
public class ConceptToStringConverter implements Converter<Concept, String> {
	
	
	/**
	 * {@inheritDoc}
	 * The method convert Concept object to String
	 * 
	 */
	@Override
	public String convert(Concept concept)
	{
		return concept.getSourceURI();
	}

}
