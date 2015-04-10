package edu.asu.qstore4s.search.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.elements.ISearchExpression;
import edu.asu.qstore4s.search.elements.factory.ISearchExpressionFactory;
import edu.asu.qstore4s.search.elements.impl.SearchExpression;

@Service
public class SearchExpressionFactory implements ISearchExpressionFactory {

	@Override
	public ISearchExpression createSearchExpression(String expression)
	{
		ISearchExpression expressionObject = new SearchExpression();
		expressionObject.setExpression(expression);
		return expressionObject;
	}
}
