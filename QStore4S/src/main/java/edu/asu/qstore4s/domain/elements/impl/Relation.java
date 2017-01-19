package edu.asu.qstore4s.domain.elements.impl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import edu.asu.qstore4s.db.neo4j.converters.SourceReferenceConverter;
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
	
	@Relationship(type="hasSubject", direction=Relationship.OUTGOING)
	private CreationEvent subject;
	
	@Relationship(type="hasObject", direction=Relationship.OUTGOING)
	private CreationEvent object;
	
	@Relationship(type="hasPredicate", direction=Relationship.OUTGOING)
	private AppellationEvent predicate;
	
	@Property(name = "source_reference")
	@Convert(SourceReferenceConverter.class)
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

	public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

}
