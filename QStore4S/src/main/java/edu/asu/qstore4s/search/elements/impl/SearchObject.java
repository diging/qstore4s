package edu.asu.qstore4s.search.elements.impl;

import java.util.ArrayList;
import java.util.List;

import edu.asu.qstore4s.search.elements.IPropertyObject;
import edu.asu.qstore4s.search.elements.ISearchObject;

/**
 * This file contains the definition of SearchObject class.
 * @author Bhargav Desai
 *
 */


public class SearchObject implements ISearchObject {
	
	private String type;
	private List<IPropertyObject> properties;
	private String connector;
	
	public SearchObject() {
		properties = new ArrayList<IPropertyObject>();
		connector = "and";
	}
	
	@Override
	public String getType()
	{
		return type;
	}
	@Override
	public void setType(String elementType)
	{
		this.type = elementType;
	}
	
	@Override
	public List<IPropertyObject> getProperties()
	{
		return properties;
	}
	
	@Override
	public void setProperties(List<IPropertyObject> propertiesList)
	{
		this.properties = propertiesList;
	}
	@Override
	public void setConnector(String connector){
		this.connector = connector;
	}
	
	@Override
	public String getConnector(){
		return connector;
	}

}
