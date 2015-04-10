package edu.asu.qstore4s.domain.elements.impl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import edu.asu.qstore4s.domain.elements.IRelation;
import edu.asu.qstore4s.domain.elements.ISourceReference;
import edu.asu.qstore4s.domain.events.IAppellationEvent;
import edu.asu.qstore4s.domain.events.ICreationEvent;
import edu.asu.qstore4s.domain.events.impl.AppellationEvent;
import edu.asu.qstore4s.domain.events.impl.CreationEvent;

/**
 * This file contains the definition of Relation class.
 *
 */
@XmlRootElement

@NodeEntity
public class Relation extends Element implements IRelation {

	@GraphId
	Long graphId;

	@Fetch
	@RelatedTo(type="hasSubject", direction=Direction.OUTGOING, elementClass=CreationEvent.class)
	private ICreationEvent subject;
	
	@Fetch
	@RelatedTo(type="hasObject", direction=Direction.OUTGOING, elementClass=CreationEvent.class)
	private ICreationEvent object;
	
	@Fetch
	@RelatedTo(type="hasPredicate", direction=Direction.OUTGOING , elementClass=AppellationEvent.class)
	private IAppellationEvent predicate;
	
	
	
	@GraphProperty
	private ISourceReference source_reference;
	
	@Override
	@XmlElement(type=CreationEvent.class)
	public ICreationEvent getSubject() {
		return subject;
	}

	@Override
	public void setSubject(ICreationEvent subject) {
		this.subject = subject;
	}

	@Override
	@XmlElement(type=CreationEvent.class)
	public ICreationEvent getObject() {
		return object;
	}

	@Override
	public void setObject(ICreationEvent object) {
		this.object = object;
	}

	@Override
	@XmlElement(type=AppellationEvent.class)
	public IAppellationEvent getPredicate() {
		return predicate;
	}

	@Override
	public void setPredicate(IAppellationEvent predicate) {
		this.predicate = predicate;
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
	
	public static class Adapter extends XmlAdapter<Relation,IRelation> {
		public IRelation unmarshal(Relation v) { return v; }
		public Relation marshal(IRelation v) { return (Relation)v; }
	}
}
