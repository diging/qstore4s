package edu.asu.qstore4s.search.events.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.events.ISearchRelationEvent;
import edu.asu.qstore4s.search.events.factory.ISearchRelationEventFactory;
import edu.asu.qstore4s.search.events.impl.SearchRelationEvent;
@Service

/**
 * 
 * @author Bhargav Desai
 *
 */

public class SearchRelationEventFactory implements ISearchRelationEventFactory {

	@Override
	public ISearchRelationEvent createRelationEvent()
	{
		ISearchRelationEvent relationEventObject = new SearchRelationEvent();
		return relationEventObject;
	}
	
}
