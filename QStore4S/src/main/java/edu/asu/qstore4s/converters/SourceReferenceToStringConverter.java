package edu.asu.qstore4s.converters;

import org.springframework.core.convert.converter.Converter;

import edu.asu.qstore4s.domain.elements.ISourceReference;
/**
 * 
 * @author Bhargav Desai
 *
 */
public class SourceReferenceToStringConverter implements Converter<ISourceReference, String> {
   
	/**
	 * {@inheritDoc}
	 * The method convert SourceReference object to String.
	 * 
	 */
	@Override
	public String convert(ISourceReference sourceReference)
	{
		return sourceReference.getSourceURI();
	}
}
