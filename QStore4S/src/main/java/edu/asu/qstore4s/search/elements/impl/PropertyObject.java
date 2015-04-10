package edu.asu.qstore4s.search.elements.impl;

import edu.asu.qstore4s.search.elements.IPropertyObject;
/**
 *This file contains the definition of PropertyObject class. 
 * @author Bhargav Desai
 *
 */


public class PropertyObject implements IPropertyObject {
	
	private String value;
	
	@Override
	public void setValue(String value)
	{
		this.value = value;
	}
	@Override
	public String getValue()
	{
		return value;
	}
}
