package edu.asu.qstore4s.domain.elements.impl;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import edu.asu.qstore4s.domain.elements.ISourceReference;

/**
 * This file contains the definition of SourceReference class.
 *
 */
@XmlRootElement
public class SourceReference extends Element implements ISourceReference {

	private String sourceURI;
	
	public SourceReference() {

	sourceURI="";
	}
	
	@Override
	public String getSourceURI() {
		return sourceURI;
	}

	@Override
	public void setSourceURI(String uri) {
		this.sourceURI = uri;
	}

	public static class Adapter extends XmlAdapter<SourceReference,ISourceReference> {
		public ISourceReference unmarshal(SourceReference v) { return v; }
		public SourceReference marshal(ISourceReference v) { return (SourceReference)v; }
	}
}
