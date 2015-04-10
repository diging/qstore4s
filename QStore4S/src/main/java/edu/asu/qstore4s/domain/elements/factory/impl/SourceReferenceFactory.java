package edu.asu.qstore4s.domain.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.ISourceReference;
import edu.asu.qstore4s.domain.elements.factory.ISourceReferenceFactory;
import edu.asu.qstore4s.domain.elements.impl.SourceReference;

/**
 * This is the factory class for SourceReference element. 
 * This is used to instantiate SourceReference class.
 * @author Veena Borannagowda
 *
 */
@Service
public class SourceReferenceFactory implements ISourceReferenceFactory{
	@Override
	public ISourceReference createSourceReference()
	{
		ISourceReference sourceReferenceObject = new SourceReference();
		return sourceReferenceObject;
	}
	
	@Override
	public ISourceReference createSourceReference(String sourceUri)
	{
		ISourceReference sourceReferenceObject = new SourceReference();
		if(sourceUri==null){
			sourceReferenceObject.setSourceURI("");
		}
		else{
		sourceReferenceObject.setSourceURI(sourceUri);}
		return sourceReferenceObject;
	}
}
