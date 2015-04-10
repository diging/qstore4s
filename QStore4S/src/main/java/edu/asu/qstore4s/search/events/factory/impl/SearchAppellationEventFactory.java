package edu.asu.qstore4s.search.events.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.events.ISearchAppellationEvent;
import edu.asu.qstore4s.search.events.factory.ISearchAppellationEventFactory;
import edu.asu.qstore4s.search.events.impl.SearchAppellationEvent;

/**
 * 
 * @author Bhargav Desai
 *
 */

@Service
public class SearchAppellationEventFactory implements ISearchAppellationEventFactory{
	
	@Override
	public ISearchAppellationEvent createAppellationEvent()
	{
		ISearchAppellationEvent appellationObject = new SearchAppellationEvent();
		return appellationObject;
	}


}
