package edu.asu.qstore4s.search.elements.factory;

import edu.asu.qstore4s.search.elements.ISearchFormattedPointer;

public interface ISearchFormattedPointerFactory {

	ISearchFormattedPointer createSearchFormattedPointer(String string);

	ISearchFormattedPointer createSearchFormattedPointer();

}
