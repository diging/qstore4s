package edu.asu.qstore4s.search.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.elements.ISearchFormattedPointer;
import edu.asu.qstore4s.search.elements.factory.ISearchFormattedPointerFactory;
import edu.asu.qstore4s.search.elements.impl.SearchFormattedPointer;

@Service
public class SearchFormattedPointerFactory implements ISearchFormattedPointerFactory{

	@Override
	public ISearchFormattedPointer createSearchFormattedPointer()
	{
		ISearchFormattedPointer formattedPointerObject = new SearchFormattedPointer();
		return formattedPointerObject;
	}

	@Override
	public ISearchFormattedPointer createSearchFormattedPointer(String string) {
		ISearchFormattedPointer formattedPointerObject = new SearchFormattedPointer();
		formattedPointerObject.setFormattedPointer(string);
		return formattedPointerObject;
	}
}
