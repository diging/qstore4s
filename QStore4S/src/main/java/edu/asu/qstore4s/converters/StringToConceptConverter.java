package edu.asu.qstore4s.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.IConcept;
import edu.asu.qstore4s.domain.elements.factory.IConceptFactory;
/**
 * 
 * @author Bhargav Desai
 *
 */
@Service
public class StringToConceptConverter implements Converter<String, IConcept> {
	@Autowired
	IConceptFactory conceptFactory;
	
	
	/**
	 * {@inheritDoc}
	 * The method convert String object to Concept object.
	 * 
	 */	@Override
	public IConcept convert(String source)
	{
		IConcept concept = conceptFactory.createConcept(source);
		return concept;
	}

}
