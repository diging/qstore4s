package edu.asu.qstore4s.search.elements.impl;

import edu.asu.qstore4s.search.elements.ISearchExpression;
/**
 * This file contains the definition of SearchExpression class.
 * @author Bhargav Desai
 *
 */


public class SearchExpression extends SearchElement  implements ISearchExpression {

	private String expression;
	


		
		
	
	@Override
	public String getExpression(){
		return expression;
	}
	@Override
	public void setExpression(String expression){
		this.expression = expression;
	}
	
}
