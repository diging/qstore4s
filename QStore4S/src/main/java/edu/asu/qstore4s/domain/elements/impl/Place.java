package edu.asu.qstore4s.domain.elements.impl;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import edu.asu.qstore4s.domain.elements.IPlace;

/**
 * This file contains the definition of Place class.
 *
 */
public class Place extends Concept implements IPlace {
	
	public static class Adapter extends XmlAdapter<Place,IPlace> {
		public IPlace unmarshal(Place v) { return v; }
		public Place marshal(IPlace v) { return (Place)v; }
	}
}
