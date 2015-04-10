package edu.asu.qstore4s.search.elements;

/**
 * 
 * @author Bhargav Desai
 *
 */

import java.util.List;

public interface ISearchObject {

	String getType();

	void setType(String elementType);

	List<IPropertyObject> getProperties();

	void setProperties(List<IPropertyObject> propertiesList);

	void setConnector(String connector);

	String getConnector();

}
