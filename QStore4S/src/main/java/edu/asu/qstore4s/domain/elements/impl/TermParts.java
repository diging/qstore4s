package edu.asu.qstore4s.domain.elements.impl;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import edu.asu.qstore4s.db.neo4j.converters.SourceReferenceConverter;

/**
 * This file contains the definition of TermParts class.
 *
 */
@XmlRootElement
@NodeEntity
public class TermParts extends Element {

	@GraphId
	Long graphId;
	
	@Relationship(type="containsTerm", direction=Relationship.OUTGOING)
	private Set<TermPart> termParts;
	
	@Property(name = "source_reference")
	@Convert(SourceReferenceConverter.class)
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
