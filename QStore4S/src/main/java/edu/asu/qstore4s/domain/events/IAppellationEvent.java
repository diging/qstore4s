package edu.asu.qstore4s.domain.events;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.data.neo4j.annotation.NodeEntity;

import edu.asu.qstore4s.domain.elements.ITerm;
import edu.asu.qstore4s.domain.events.impl.AppellationEvent;

@XmlJavaTypeAdapter(AppellationEvent.Adapter.class)
@NodeEntity
public interface IAppellationEvent extends ICreationEvent {

	public ITerm getTerm();
	public void setTerm(ITerm term);
}
