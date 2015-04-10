package edu.asu.qstore4s.search.elements.factory;

import edu.asu.qstore4s.search.elements.ISearchActor;

public interface ISearchActorFactory {

	ISearchActor createSearchActor();
	ISearchActor createSearchActor(String sourceUri);
}
