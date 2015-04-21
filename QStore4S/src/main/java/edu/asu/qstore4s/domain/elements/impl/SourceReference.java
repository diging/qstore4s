package edu.asu.qstore4s.domain.elements.impl;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.TypeAlias;

/**
 * This file contains the definition of SourceReference class.
 *
 */
@XmlRootElement
public class SourceReference extends Element {

	private String sourceURI;
	
	public SourceReference() {

	sourceURI="";
	}
	
	public String getSourceURI() {
		return sourceURI;
	}

	public void setSourceURI(String uri) {
		this.sourceURI = uri;
	}

}
