package edu.asu.qstore4s.domain.elements.impl;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This file contains the definition of Concept class.
 *
 */
@XmlRootElement
public class Concept extends Element {

	private String sourceURI;
	
	public String getSourceURI() {
		return sourceURI;
	}

	public void setSourceURI(String sourceURI) {
		this.sourceURI = sourceURI;
	}

}
