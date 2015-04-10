package edu.asu.qstore4s.domain.elements.impl;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import edu.asu.qstore4s.domain.elements.ISourceReference;
import edu.asu.qstore4s.domain.elements.ITermPart;
import edu.asu.qstore4s.domain.elements.ITermParts;

/**
 * This file contains the definition of TermParts class.
 *
 */
@XmlRootElement

@NodeEntity
public class TermParts extends Element implements ITermParts {

	@GraphId
	Long graphId;

	@Fetch
	@RelatedTo(type="containsTerm", direction=Direction.OUTGOING, elementClass = TermPart.class)
	private Set<ITermPart> termParts;
	
	@GraphProperty
	private ISourceReference source_reference;
	
	public TermParts() {
		termParts = new HashSet<ITermPart>();
	}
	
	@Override
	@XmlElementRefs({ @XmlElementRef(type=TermPart.class)}) 
	public Set<ITermPart> getTermParts() {
		return termParts;
	}

	@Override
	@XmlElement(type=SourceReference.class)
	public ISourceReference getReferencedSource() {
		return source_reference;
	}

	@Override
	public void setTermParts(Set<ITermPart> parts) {
		this.termParts = parts;
	}

	@Override
	public void setReferencedSource(ISourceReference reference) {
		this.source_reference = reference;
	}

	public static class Adapter extends XmlAdapter<TermParts,ITermParts> {
		public ITermParts unmarshal(TermParts v) { return v; }
		public TermParts marshal(ITermParts v) { return (TermParts)v; }
	}
}
