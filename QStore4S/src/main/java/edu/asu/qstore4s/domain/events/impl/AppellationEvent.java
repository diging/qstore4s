package edu.asu.qstore4s.domain.events.impl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import edu.asu.qstore4s.domain.elements.ITerm;
import edu.asu.qstore4s.domain.elements.impl.Term;
import edu.asu.qstore4s.domain.events.IAppellationEvent;

/**
 * This file contains the definition of AppellationEvent class.
 *
 */
@XmlRootElement
public class AppellationEvent extends CreationEvent implements
		IAppellationEvent {
 

	
	@RelatedTo(type="hasTerm", direction=Direction.OUTGOING, elementClass= Term.class)
	@Fetch	private ITerm term;
	
	@Override
	@XmlElement(type=Term.class)
	public ITerm getTerm() {
		return term;
	}

	@Override
	public void setTerm(ITerm term) {
		this.term = term;
	}

	public static class Adapter extends XmlAdapter<AppellationEvent,IAppellationEvent> {
		public IAppellationEvent unmarshal(AppellationEvent v) { return v; }
		public AppellationEvent marshal(IAppellationEvent v) { return (AppellationEvent)v; }
	}
}
