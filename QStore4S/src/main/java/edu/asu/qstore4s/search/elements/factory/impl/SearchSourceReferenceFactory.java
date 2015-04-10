package edu.asu.qstore4s.search.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.elements.ISearchSourceReference;
import edu.asu.qstore4s.search.elements.factory.ISearchSourceReferenceFactory;
import edu.asu.qstore4s.search.elements.impl.SearchSourceReference;

@Service
public class SearchSourceReferenceFactory implements ISearchSourceReferenceFactory {

	@Override
	public ISearchSourceReference createSearchSourceReference()
	{
		ISearchSourceReference sourceReferenceObject = new SearchSourceReference();
		return sourceReferenceObject;
	}
	
	@Override
	public ISearchSourceReference createSearchSourceReference(String sourceUri)
	{
		ISearchSourceReference sourceReferenceObject = new SearchSourceReference();
		if(sourceUri==null){
			sourceReferenceObject.setSourceURI("");
		}
		else{
		sourceReferenceObject.setSourceURI(sourceUri);}
		return sourceReferenceObject;
	}
}
