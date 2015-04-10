package edu.asu.qstore4s.domain.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.IConcept;
import edu.asu.qstore4s.domain.elements.factory.IConceptFactory;
import edu.asu.qstore4s.domain.elements.impl.Concept;

/**
 * This is the factory class for Concept element. 
 * This is used to instantiate Concept class.
 * @author Veena Borannagowda
 *
 */
@Service
public class ConceptFactory implements IConceptFactory {
	@Override
	public IConcept createConcept()
	{
		IConcept conceptObject = new Concept();
		return conceptObject;
	}
	
	@Override
	public IConcept createConcept(String sourceUri)
	{
		IConcept conceptObject = new Concept();
		if(sourceUri==null){
			conceptObject.setSourceURI("");
			
		}
		else{
		conceptObject.setSourceURI(sourceUri);}
		return conceptObject;
	}

}
