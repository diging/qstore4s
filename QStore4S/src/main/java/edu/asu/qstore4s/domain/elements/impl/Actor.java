package edu.asu.qstore4s.domain.elements.impl;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import edu.asu.qstore4s.domain.elements.IActor;

/**
 * This file contains the definition of Actor class.
 *
 */

public class Actor extends Concept implements IActor {
	
	
	public static class Adapter extends XmlAdapter<Actor,IActor> {
		public IActor unmarshal(Actor v) { return v; }
		public Actor marshal(IActor v) { return (Actor)v; }
	}
}
