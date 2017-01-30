package edu.asu.qstore4s.domain.events.impl;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import edu.asu.qstore4s.db.neo4j.converters.ActorConverter;
import edu.asu.qstore4s.db.neo4j.converters.SourceReferenceConverter;
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
	
    @Property(name = "source_reference")
	@Convert(SourceReferenceConverter.class)
	private SourceReference source_reference;
	
	private Set<CreationEvent> predecessors;
	
	@Property(name = "interpretation_creator")
	@Convert(ActorConverter.class)
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
	
	public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

}
