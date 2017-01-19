package edu.asu.qstore4s.domain.elements.impl;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This file contains the definition of VocabularyEntry class.
 *
 */
@XmlRootElement
public class VocabularyEntry extends Element {

	private String term;
	private String sourceURI;

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	
	public String getSourceURI() {
		return sourceURI;
	}

	public void setSourceURI(String sourceURI) {
		this.sourceURI = sourceURI;
	}

}
