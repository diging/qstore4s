package edu.asu.qstore4s.domain.elements.impl;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import edu.asu.qstore4s.domain.elements.IVocabularyEntry;

/**
 * This file contains the definition of VocabularyEntry class.
 *
 */
@XmlRootElement
public class VocabularyEntry extends Element implements IVocabularyEntry {

	private String term;
	private String sourceURI;

	@Override
	public String getTerm() {
		return term;
	}

	@Override
	public void setTerm(String term) {
		this.term = term;
	}
	
	
	@Override
	public String getSourceURI() {
		return sourceURI;
	}

	@Override
	public void setSourceURI(String sourceURI) {
		this.sourceURI = sourceURI;
	}

	public static class Adapter extends XmlAdapter<VocabularyEntry,IVocabularyEntry> {
		public IVocabularyEntry unmarshal(VocabularyEntry v) { return v; }
		public VocabularyEntry marshal(IVocabularyEntry v) { return (VocabularyEntry)v; }
	}
}
