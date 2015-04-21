package edu.asu.qstore4s.domain.elements.impl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import edu.asu.qstore4s.domain.events.impl.AppellationEvent;
import edu.asu.qstore4s.domain.events.impl.CreationEvent;

/**
 * This file contains the definition of Relation class.
 *
 */
@XmlRootElement
@NodeEntity
public class Relation extends Element {

	@GraphId
	Long graphId;
	
	@Fetch
	@RelatedTo(type="hasSubject", direction=Direction.OUTGOING)
	private CreationEvent subject;
	
	@Fetch
	@RelatedTo(type="hasObject", direction=Direction.OUTGOING)
	private CreationEvent object;
	
	@Fetch
	@RelatedTo(type="hasPredicate", direction=Direction.OUTGOING)
	private AppellationEvent predicate;
	
	
	
	@GraphProperty
	private SourceReference source_reference;
	
	@XmlElement(type=CreationEvent.class)
	public CreationEvent getSubject() {
		return subject;
	}

	public void setSubject(CreationEvent subject) {
		this.subject = subject;
	}

	@XmlElement(type=CreationEvent.class)
	public CreationEvent getObject() {
		return object;
	}

	public void setObject(CreationEvent object) {
		this.object = object;
	}

	@XmlElement(type=AppellationEvent.class)
	public AppellationEvent getPredicate() {
		return predicate;
	}

	public void setPredicate(AppellationEvent predicate) {
		this.predicate = predicate;
	}

	@XmlElement(type=SourceReference.class)
	public SourceReference getSourceReference() {
		return source_reference;
	}

	public void setSourceReference(SourceReference reference) {
		this.source_reference = reference;
	}

}
