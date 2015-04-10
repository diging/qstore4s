package edu.asu.qstore4s.domain.elements.impl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;

import edu.asu.qstore4s.domain.elements.ISourceReference;
import edu.asu.qstore4s.domain.elements.ITermPart;
import edu.asu.qstore4s.domain.elements.IVocabularyEntry;

/**
 * This file contains the definition of TermPart class.
 *
 */

@XmlRootElement
@NodeEntity
public class TermPart extends Element implements ITermPart {

	@GraphId
	Long graphId;

	
	
	private String formatted_pointer;
	private String format;
	private String expression;

	private int position;
	
	@GraphProperty
	private IVocabularyEntry normalization;

	
	@GraphProperty
	private ISourceReference source_reference;
	
	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public String getExpression() {
		return expression;
	}

	@Override
	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public void setExpression(String expression) {
		this.expression = expression;
	}

	public void setNormalization(IVocabularyEntry normalization) {
		this.normalization = normalization;
	}

	@XmlElement(type=VocabularyEntry.class)
	public IVocabularyEntry getNormalization() {
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
	
	@Override
	@XmlElement(type=SourceReference.class)
	public ISourceReference getSourceReference() {
		return source_reference;
	}

	@Override
	public void setSourceReference(ISourceReference reference) {
		this.source_reference = reference;
	}
	
	public static class Adapter extends XmlAdapter<TermPart,ITermPart> {
		public ITermPart unmarshal(TermPart v) { return v; }
		public TermPart marshal(ITermPart v) { return (TermPart)v; }
	}
}
