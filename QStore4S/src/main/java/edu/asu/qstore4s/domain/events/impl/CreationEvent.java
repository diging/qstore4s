package edu.asu.qstore4s.domain.events.impl;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;

import edu.asu.qstore4s.domain.elements.impl.Actor;
import edu.asu.qstore4s.domain.elements.impl.Element;
import edu.asu.qstore4s.domain.elements.impl.SourceReference;

/**
 * This file contains the definition of CreationEvent class.
 *
 */
@XmlRootElement
@NodeEntity
public class CreationEvent extends Element {

	@GraphId
	Long graphId;
	

	@GraphProperty
	private SourceReference source_reference;
	
	
	private Set<CreationEvent> predecessors;
	
	@GraphProperty
	private Actor interpretation_creator;
	
	public CreationEvent() {
		predecessors = new HashSet<CreationEvent>();
	}
	
	@XmlElement(type=SourceReference.class)
	public SourceReference getSourceReference() {
		return source_reference;
	}

	public void setSourceReference(SourceReference reference) {
		this.source_reference = reference;
	}

	@XmlAnyElement
	public Set<CreationEvent> getPredecessors() {
		return predecessors;
	}

	public void setPredecessors(Set<CreationEvent> predecessors) {
		this.predecessors = predecessors;
	}

	public Actor getInterpretationCreator() {
		return interpretation_creator;
	}

	public void setInterpretationCreator(Actor interpretationCreator) {
		this.interpretation_creator = interpretationCreator;
	}

}
