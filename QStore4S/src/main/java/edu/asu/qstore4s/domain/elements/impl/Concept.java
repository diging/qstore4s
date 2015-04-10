package edu.asu.qstore4s.domain.elements.impl;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import edu.asu.qstore4s.domain.elements.IConcept;

/**
 * This file contains the definition of Concept class.
 *
 */
@XmlRootElement
public class Concept extends Element implements IConcept {

	private String sourceURI;

	@Override
	public String getSourceURI() {
		return sourceURI;
	}

	@Override
	public void setSourceURI(String sourceURI) {
		this.sourceURI = sourceURI;
	}

	public static class Adapter extends XmlAdapter<Concept,IConcept> {
		public IConcept unmarshal(Concept v) { return v; }
		public Concept marshal(IConcept v) { return (Concept)v; }
	}
}
