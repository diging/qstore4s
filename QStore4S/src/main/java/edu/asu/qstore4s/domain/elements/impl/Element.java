package edu.asu.qstore4s.domain.elements.impl;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.springframework.data.annotation.Transient;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.support.index.IndexType;

import edu.asu.qstore4s.domain.elements.IActor;
import edu.asu.qstore4s.domain.elements.IElement;
import edu.asu.qstore4s.domain.elements.IPlace;

/**
 * This file contains the definition of Element class.
 *
 */


public class Element implements IElement {
	
	
	
	
	@Indexed(unique=true, indexType=IndexType.SIMPLE)
	private String id;
	
	@Transient
	private String refId;
	
	@Transient
	private String internal_refId;
	
	@Transient
	private String external_refId;
	
	@Transient
	private Boolean isIdAssigned;
	
	@GraphProperty(propertyType = String.class)
	private IActor creator;
	
	@GraphProperty
	private Date creation_date;
	
	@GraphProperty
	private IPlace creation_place;
	
	@Override
	public String getId() {
		return id;
	}
	
	@Override	
	public String getInternal_refId() {
		return internal_refId;
	}

	@Override
	public void setInternal_refId(String internal_refId) {
		this.internal_refId = internal_refId;
	}

	@Override
	public String getExternal_refId() {
		return external_refId;
	}

	@Override
	public void setExternal_refId(String external_refId) {
		this.external_refId = external_refId;
	}
	
    @Override
	public void setRefId(String refId) {
		this.refId = refId;
	}
		
    @Override
    public String getRefId(){
    	return refId;
    }
    
	@Override
	@XmlElement(type=Actor.class)
	public IActor getCreator() {
		return creator;
	}

	@Override
	public Date getCreationDate() {
		return creation_date;
	}

	@Override
	@XmlElement(type=Place.class)
	public IPlace getCreationPlace() {
		return creation_place;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public void setCreator(IActor actor) {
		this.creator = actor;
	}

	@Override
	public void setCreationDate(Date date) {
		this.creation_date = date;
	}

	@Override
	public void setCreationPlace(IPlace place) {
		this.creation_place = place;
	}

	
	
//	//@Override
//	public boolean equals(Object obj) {
//		if (!(obj instanceof Element))
//			return false;
//		
//		return ((Element)obj).getId().equals(getId());
//	}



	public static class Adapter extends XmlAdapter<Element,IElement> {
		public IElement unmarshal(Element v) { return v; }
		public Element marshal(IElement v) { return (Element)v; }
	}


	@Override
	public boolean isIdAssigned() {
		
		if(isIdAssigned==null)
			return false;
		else
		return isIdAssigned;
	}

	@Override
	public void setIdAssigned(boolean isIdAssigned) {
		this.isIdAssigned = isIdAssigned;
	}
}
