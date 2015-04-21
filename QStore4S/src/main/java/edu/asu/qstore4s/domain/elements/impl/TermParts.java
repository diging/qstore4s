package edu.asu.qstore4s.domain.elements.impl;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * This file contains the definition of TermParts class.
 *
 */
@XmlRootElement
@NodeEntity
public class TermParts extends Element {

	@GraphId
	Long graphId;

	@Fetch
	@RelatedTo(type="containsTerm", direction=Direction.OUTGOING)
	private Set<TermPart> termParts;
	
	@GraphProperty
	private SourceReference source_reference;
	
	public TermParts() {
		termParts = new HashSet<TermPart>();
	}
	
	@XmlElementRefs({ @XmlElementRef(type=TermPart.class)}) 
	public Set<TermPart> getTermParts() {
		return termParts;
	}

	@XmlElement(type=SourceReference.class)
	public SourceReference getReferencedSource() {
		return source_reference;
	}

	public void setTermParts(Set<TermPart> parts) {
		this.termParts = parts;
	}

	public void setReferencedSource(SourceReference reference) {
		this.source_reference = reference;
	}

}
