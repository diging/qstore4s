package edu.asu.qstore4s.domain.elements.impl;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.data.annotation.Transient;

import edu.asu.qstore4s.db.neo4j.converters.ActorConverter;
import edu.asu.qstore4s.db.neo4j.converters.PlaceConverter;

/**
 * This file contains the definition of Element class.
 *
 */
public class Element {
	
	private String id;
	
	@Transient
	private String refId;
	
	@Transient
	private String internal_refId;
	
	@Transient
	private String external_refId;
	
	@Transient
	private Boolean isIdAssigned;
	
	@Property(name = "creator")
	@Convert(ActorConverter.class)
	private Actor creator;
	
	@Property(name = "creation_date")
	@DateLong
	private Date creation_date;
	
	@Property(name = "creation_place")
	@Convert(PlaceConverter.class)
	private Place creation_place;
	
	public String getId() {
		return id;
	}
	
	public String getInternal_refId() {
		return internal_refId;
	}

	public void setInternal_refId(String internal_refId) {
		this.internal_refId = internal_refId;
	}

	public String getExternal_refId() {
		return external_refId;
	}

	public void setExternal_refId(String external_refId) {
		this.external_refId = external_refId;
	}
	
    public void setRefId(String refId) {
		this.refId = refId;
	}
		
    public String getRefId(){
    	return refId;
    }
    
	@XmlElement(type=Actor.class)
	public Actor getCreator() {
		return creator;
	}

	public Date getCreationDate() {
		return creation_date;
	}

	@XmlElement(type=Place.class)
	public Place getCreationPlace() {
		return creation_place;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setCreator(Actor actor) {
		this.creator = actor;
	}

	public void setCreationDate(Date date) {
		this.creation_date = date;
	}

	public void setCreationPlace(Place place) {
		this.creation_place = place;
	}


	public boolean isIdAssigned() {
		
		if(isIdAssigned==null)
			return false;
		else
		return isIdAssigned;
	}

	public void setIdAssigned(boolean isIdAssigned) {
		this.isIdAssigned = isIdAssigned;
	}
}
