package edu.asu.qstore4s.domain.elements.impl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import edu.asu.qstore4s.db.neo4j.converters.SourceReferenceConverter;
import edu.asu.qstore4s.db.neo4j.converters.VocabularyEntryConverter;

/**
 * This file contains the definition of TermPart class.
 *
 */

@XmlRootElement
@NodeEntity
public class TermPart extends Element {

	@GraphId
	Long graphId;
	
	private String formatted_pointer;
	private String format;
	private String expression;

	private Integer position;
	
	@Property(name = "normalization")
	@Convert(VocabularyEntryConverter.class)
	private VocabularyEntry normalization;

	
	@Property(name = "source_reference")
	@Convert(SourceReferenceConverter.class)
	private SourceReference source_reference;
	
	public Integer getPosition() {
		return position;
	}

	public String getExpression() {
		return expression;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public void setNormalization(VocabularyEntry normalization) {
		this.normalization = normalization;
	}

	@XmlElement(type=VocabularyEntry.class)
	public VocabularyEntry getNormalization() {
		return normalization;
	}

	public void setFormattedPointer(String formattedPointer) {
		this.formatted_pointer = formattedPointer;
	}

	public String getFormattedPointer() {
		return formatted_pointer;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFormat() {
		return format;
	}
	
	@XmlElement(type=SourceReference.class)
	public SourceReference getSourceReference() {
		return source_reference;
	}

	public void setSourceReference(SourceReference reference) {
		this.source_reference = reference;
	}
	
}
