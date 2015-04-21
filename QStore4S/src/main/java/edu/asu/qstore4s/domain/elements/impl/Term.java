package edu.asu.qstore4s.domain.elements.impl;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * This file contains the definition of Term class.
 *
 */
@XmlRootElement
@NodeEntity
public class Term extends Element {
	
	@GraphId
	Long graphId;
	
	@GraphProperty
	private Concept interpretation;
	
	@GraphProperty
	private VocabularyEntry normalized_representation;
	
	@Fetch
	@RelatedTo(type="hasTermParts", direction=Direction.OUTGOING)
	private TermParts printedRepresentation;
	
	@GraphProperty(propertyType=String.class)
	private Boolean certain;
	
	
	@Fetch
	@RelatedTo(type="referencedTerm", direction=Direction.OUTGOING, elementClass=Term.class)
	private Set<Term> referencedTerms;
	
	
	
	@GraphProperty
	private SourceReference source_reference;
	
	public Term() {
		referencedTerms = new HashSet<Term>();
	}

	public Concept getInterpretation() {
		return interpretation;
	}

	@XmlElement(type=Concept.class)
	public void setInterpretation(Concept concept) {
		this.interpretation = concept;
	}

	@XmlElement(type=VocabularyEntry.class)
	public VocabularyEntry getNormalizedRepresentation() {
		return normalized_representation;
	}
		
	public void setNormalizedRepresentation(VocabularyEntry entry) {
		this.normalized_representation = entry;
	}

	@XmlElement(type=TermParts.class)
	public TermParts getPrintedRepresentation() {
		return printedRepresentation;
	}

	public void setPrintedRepresentation(TermParts parts) {
		this.printedRepresentation = parts;
	}

	public Boolean isCertain() {
		return certain;
	}
	
	/**
	 * Getter method for hibernate.
	 **/
	public Boolean getCertain() {
		return certain;
	}

	public void setIsCertain(Boolean certainty) {
		this.certain = certainty;
	}
	
	/**
	  * Setter method for hibernate.
	 **/
	public void setCertain(boolean certainty) {
		this.certain = certainty;
	}

	@XmlElement(type=Term.class)
	public Set<Term> getReferencedTerms() {
		return referencedTerms;
	}

	public void setReferencedTerms(Set<Term> terms) {
		this.referencedTerms = terms;
	}
	
	@XmlElement(type=SourceReference.class)
	public SourceReference getSourceReference() {
		return source_reference;
	}

	public void setSourceReference(SourceReference reference) {
		this.source_reference = reference;
	}

}
