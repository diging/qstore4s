package edu.asu.qstore4s.search.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.elements.ISearchFormat;
import edu.asu.qstore4s.search.elements.factory.ISearchFormatFactory;
import edu.asu.qstore4s.search.elements.impl.SearchFormat;

@Service
public class SearchFormatFactory implements ISearchFormatFactory {

	@Override
	public ISearchFormat createSearchFormat()
	{
		ISearchFormat formatObject = new SearchFormat();
		return formatObject;
	}

	@Override
	public ISearchFormat createSearchFormat(String searchFormat) {
		ISearchFormat formatObject = new SearchFormat();
		formatObject.setFormat(searchFormat);
		return formatObject;
	}
}
