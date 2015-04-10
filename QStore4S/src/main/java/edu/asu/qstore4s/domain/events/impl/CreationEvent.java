package edu.asu.qstore4s.domain.events.impl;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;

import edu.asu.qstore4s.domain.elements.IActor;
import edu.asu.qstore4s.domain.elements.ISourceReference;
import edu.asu.qstore4s.domain.elements.impl.Element;
import edu.asu.qstore4s.domain.elements.impl.SourceReference;
import edu.asu.qstore4s.domain.events.ICreationEvent;

/**
 * This file contains the definition of CreationEvent class.
 *
 */
@XmlRootElement
@NodeEntity
public class CreationEvent extends Element implements ICreationEvent {

	@GraphId
	Long graphId;

	@GraphProperty
	private ISourceReference source_reference;
	
	
	private Set<ICreationEvent> predecessors;
	
	@GraphProperty
	private IActor interpretation_creator;
	
	public CreationEvent() {
		predecessors = new HashSet<ICreationEvent>();
	}
	
	@Override
	@XmlElement(type=SourceReference.class)
	public ISourceReference getSourceReference() {
		return source_reference;
	}

	@Override
	public void setSourceReference(ISourceReference reference) {
		this.source_reference = reference;
	}

	@Override
	@XmlAnyElement
	public Set<ICreationEvent> getPredecessors() {
		return predecessors;
	}

	@Override
	public void setPredecessors(Set<ICreationEvent> predecessors) {
		this.predecessors = predecessors;
	}

	@Override
	public IActor getInterpretationCreator() {
		return interpretation_creator;
	}

	@Override
	public void setInterpretationCreator(IActor interpretationCreator) {
		this.interpretation_creator = interpretationCreator;
	}

	public static class Adapter extends XmlAdapter<CreationEvent,ICreationEvent> {
		public ICreationEvent unmarshal(CreationEvent v) { return v; }
		public CreationEvent marshal(ICreationEvent v) { return (CreationEvent)v; }
	}
}
