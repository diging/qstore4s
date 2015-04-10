package edu.asu.qstore4s.search.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.elements.ISearchActor;
import edu.asu.qstore4s.search.elements.factory.ISearchActorFactory;
import edu.asu.qstore4s.search.elements.impl.SearchActor;

@Service
public class SearchActoryFactory implements ISearchActorFactory{
	
	@Override
	public ISearchActor createSearchActor()
	{
		ISearchActor actorObject = new SearchActor();
		return actorObject;
	}
	
	@Override
	public ISearchActor createSearchActor(String sourceUri)
	{
		ISearchActor actorObject = new SearchActor();
		if(sourceUri==null)
		{
			actorObject.setSourceURI("");
		}
		else{
		actorObject.setSourceURI(sourceUri);}
		return actorObject;
	}

}
