package edu.asu.qstore4s.converters;

import org.springframework.core.convert.converter.Converter;

import edu.asu.qstore4s.domain.elements.IConcept;
/**
 * 
 * @author Bhargav Desai
 *
 */
public class ConceptToStringConverter implements Converter<IConcept, String> {
	
	
	/**
	 * {@inheritDoc}
	 * The method convert Concept object to String
	 * 
	 */
	@Override
	public String convert(IConcept concept)
	{
		return concept.getSourceURI();
	}

}
