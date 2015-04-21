package edu.asu.qstore4s.domain.events.impl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelatedTo;

import edu.asu.qstore4s.domain.elements.impl.Term;

/**
 * This file contains the definition of AppellationEvent class.
 *
 */
@XmlRootElement
public class AppellationEvent extends CreationEvent{
 
	@RelatedTo(type="hasTerm", direction=Direction.OUTGOING)
	@Fetch	private Term term;
	
	@XmlElement
	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}
}
