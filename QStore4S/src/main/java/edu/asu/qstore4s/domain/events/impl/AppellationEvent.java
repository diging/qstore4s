package edu.asu.qstore4s.domain.events.impl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.ogm.annotation.Relationship;

import edu.asu.qstore4s.domain.elements.impl.Term;

/**
 * This file contains the definition of AppellationEvent class.
 *
 */
@XmlRootElement
public class AppellationEvent extends CreationEvent{
 
	@Relationship(type="hasTerm", direction=Relationship.OUTGOING)
	private Term term;
	
	@XmlElement
	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}
}
