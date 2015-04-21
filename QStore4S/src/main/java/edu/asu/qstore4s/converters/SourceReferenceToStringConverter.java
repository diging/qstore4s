package edu.asu.qstore4s.converters;

import org.springframework.core.convert.converter.Converter;

import edu.asu.qstore4s.domain.elements.impl.SourceReference;
/**
 * 
 * @author Bhargav Desai
 *
 */
public class SourceReferenceToStringConverter implements Converter<SourceReference, String> {
   
	/**
	 * {@inheritDoc}
	 * The method convert SourceReference object to String.
	 * 
	 */
	@Override
	public String convert(SourceReference sourceReference)
	{
		return sourceReference.getSourceURI();
	}
}
