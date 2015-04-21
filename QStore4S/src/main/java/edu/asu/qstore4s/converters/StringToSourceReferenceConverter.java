package edu.asu.qstore4s.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.factory.impl.SourceReferenceFactory;
import edu.asu.qstore4s.domain.elements.impl.SourceReference;
/**
 * 
 * @author Bhargav Desai
 *
 */

@Service
public class StringToSourceReferenceConverter implements Converter<String, SourceReference> {
	@Autowired 
	SourceReferenceFactory sourceReferenceFactory;
	
	/**
	 * {@inheritDoc}
	 * The method convert String object to SourceReferecne object.
	 * 
	 */
	
	@Override
	public SourceReference convert(String source)
	{
		return sourceReferenceFactory.createSourceReference(source);
	}

}
