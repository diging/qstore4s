package edu.asu.qstore4s.domain.elements;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.asu.qstore4s.domain.elements.impl.Actor;

/**
 * This is the interface class for Actor class.
 *
 */
@XmlJavaTypeAdapter(Actor.Adapter.class)
public interface IActor extends IConcept {
	

}
