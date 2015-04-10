package edu.asu.qstore4s.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.ISourceReference;
import edu.asu.qstore4s.domain.elements.factory.impl.SourceReferenceFactory;
/**
 * 
 * @author Bhargav Desai
 *
 */

@Service
public class StringToSourceReferenceConverter implements Converter<String, ISourceReference> {
	@Autowired 
	SourceReferenceFactory sourceReferenceFactory;
	
	/**
	 * {@inheritDoc}
	 * The method convert String object to SourceReferecne object.
	 * 
	 */
	
	@Override
	public ISourceReference convert(String source)
	{
		ISourceReference sourcReference = sourceReferenceFactory.createSourceReference(source);
		return sourcReference;
	}

}
