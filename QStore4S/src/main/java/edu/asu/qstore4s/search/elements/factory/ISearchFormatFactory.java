package edu.asu.qstore4s.search.elements.factory;

import edu.asu.qstore4s.search.elements.ISearchFormat;

public interface ISearchFormatFactory {

	ISearchFormat createSearchFormat();

	ISearchFormat createSearchFormat(String checkForSpaces);

}
