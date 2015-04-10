package edu.asu.qstore4s.search.elements;

import java.util.Date;


/**
 * 
 * @author Bhargav Desai
 *
 */

public interface ISearchElement {

	public String getId();

	public Date getCreationDate();

	public ISearchPlace getCreationPlace();

	public void setId(String id);

	public void setCreator(ISearchActor actor);

	public void setCreationDate(Date date);

	public void setCreationPlace(ISearchPlace place);

	ISearchActor getCreator();

	String getSearchType();

	void setSearchType(String searchType);


	public String getName() ;

	public void setName(String name) ;

	
	public String getConnector() ;

	public void setConnector(String connector) ;

}
