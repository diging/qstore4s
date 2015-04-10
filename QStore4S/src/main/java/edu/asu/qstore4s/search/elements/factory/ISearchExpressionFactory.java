package edu.asu.qstore4s.search.elements.factory;

import edu.asu.qstore4s.search.elements.ISearchExpression;

public interface ISearchExpressionFactory {

	
	ISearchExpression createSearchExpression(String expression);

}
