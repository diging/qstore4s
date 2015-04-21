package edu.asu.qstore4s.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.factory.IConceptFactory;
import edu.asu.qstore4s.domain.elements.impl.Concept;
/**
 * 
 * @author Bhargav Desai
 *
 */
@Service
public class StringToConceptConverter implements Converter<String, Concept> {
	@Autowired
	IConceptFactory conceptFactory;
	
	
	/**
	 * {@inheritDoc}
	 * The method convert String object to Concept object.
	 * 
	 */	@Override
	public Concept convert(String source)
	{
		return conceptFactory.createConcept(source);
	}

}
