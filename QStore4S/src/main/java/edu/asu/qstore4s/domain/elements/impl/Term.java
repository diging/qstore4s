package edu.asu.qstore4s.domain.elements.impl;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import edu.asu.qstore4s.domain.elements.IConcept;
import edu.asu.qstore4s.domain.elements.ISourceReference;
import edu.asu.qstore4s.domain.elements.ITerm;
import edu.asu.qstore4s.domain.elements.ITermParts;
import edu.asu.qstore4s.domain.elements.IVocabularyEntry;

/**
 * This file contains the definition of Term class.
 *
 */
@XmlRootElement

@NodeEntity
public class Term extends Element implements ITerm {
	
	@GraphId
	Long graphId;

	@GraphProperty
	private IConcept interpretation;
	
	@GraphProperty
	private IVocabularyEntry normalized_representation;
	
	@Fetch
	@RelatedTo(type="hasTermParts", direction=Direction.OUTGOING, elementClass= TermParts.class)
	private ITermParts printedRepresentation;
	
	@GraphProperty(propertyType=String.class)
	private boolean certain;
	
	
	@Fetch
	@RelatedTo(type="referencedTerm", direction=Direction.OUTGOING, elementClass=Term.class)
	private Set<ITerm> referencedTerms;
	
	
	
	@GraphProperty
	private ISourceReference source_reference;
	
	public Term() {
		referencedTerms = new HashSet<ITerm>();
	}

	@Override
	public IConcept getInterpretation() {
		return interpretation;
	}

	@Override
	@XmlElement(type=Concept.class)
	public void setInterpretation(IConcept concept) {
		this.interpretation = concept;
	}

	@Override
	@XmlElement(type=VocabularyEntry.class)
	public IVocabularyEntry getNormalizedRepresentation() {
		return normalized_representation;
	}
		
	@Override
	public void setNormalizedRepresentation(IVocabularyEntry entry) {
		this.normalized_representation = entry;
	}

	@Override
	@XmlElement(type=TermParts.class)
	public ITermParts getPrintedRepresentation() {
		return printedRepresentation;
	}

	@Override
	public void setPrintedRepresentation(ITermParts parts) {
		this.printedRepresentation = parts;
	}

	@Override
	public boolean isCertain() {
		return certain;
	}
	
	/**
	 * Getter method for hibernate.
	 **/
	public boolean getCertain() {
		return certain;
	}

	@Override
	public void setIsCertain(boolean certainty) {
		this.certain = certainty;
	}
	
	/**
	  * Setter method for hibernate.
	 **/
	public void setCertain(boolean certainty) {
		this.certain = certainty;
	}

	@Override
	@XmlElement(type=Term.class)
	public Set<ITerm> getReferencedTerms() {
		return referencedTerms;
	}

	@Override
	public void setReferencedTerms(Set<ITerm> terms) {
		this.referencedTerms = terms;
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

	public static class Adapter extends XmlAdapter<Term,ITerm> {
		public ITerm unmarshal(Term v) { return v; }
		public Term marshal(ITerm v) { return (Term)v; }
	}
}
