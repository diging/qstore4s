package edu.asu.qstore4s.search.elements.impl;

import java.util.Date;

import edu.asu.qstore4s.search.elements.ISearchActor;
import edu.asu.qstore4s.search.elements.ISearchElement;
import edu.asu.qstore4s.search.elements.ISearchPlace;

/**
 * This file contains the definition of SearchElement class.
 * @author Bhargav Desai
 *
 */

public class SearchElement implements ISearchElement {

	private String id;

	private ISearchActor creator;

	private Date creation_date;

	private ISearchPlace creation_place;

	private String searchType;
	
	private String connector;
	
	private String name;

	@Override
	public String getSearchType() {
		return searchType;
	}
	
	
	public SearchElement() {
		searchType="equals";
		connector="and";
	
	}

	@Override
	public void setSearchType(String searchType) {

		if (searchType == null || searchType.equals("")) {
			this.searchType = "equals";
		} else {
			this.searchType = searchType;
		}

	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public ISearchActor getCreator() {
		return creator;
	}

	@Override
	public Date getCreationDate() {
		return creation_date;
	}

	@Override
	public ISearchPlace getCreationPlace() {
		return creation_place;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setCreator(ISearchActor actor) {
		this.creator = actor;
	}

	@Override
	public void setCreationDate(Date date) {
		this.creation_date = date;
	}

	@Override
	public void setCreationPlace(ISearchPlace place) {
		this.creation_place = place;
	}

	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String getConnector() {
		return connector;
	}

	@Override
	public void setConnector(String connector) {
		
		
		if(connector==null || connector.equals("") )
		{
			this.connector="and";
		}
		this.connector = connector;
	}

}
