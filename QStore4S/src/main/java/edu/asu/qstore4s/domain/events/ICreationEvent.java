package edu.asu.qstore4s.domain.events;

import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.data.neo4j.annotation.NodeEntity;

import edu.asu.qstore4s.domain.elements.IActor;
import edu.asu.qstore4s.domain.elements.IElement;
import edu.asu.qstore4s.domain.elements.ISourceReference;
import edu.asu.qstore4s.domain.events.impl.CreationEvent;

@XmlJavaTypeAdapter(CreationEvent.Adapter.class)
@NodeEntity
public interface ICreationEvent extends IElement {

	public ISourceReference getSourceReference();
	public void setSourceReference(ISourceReference reference);
	
	/**
	 * Should only be used for "corrections."
	 * If you define a predecessor be aware that your element
	 * is the default element to work with. 
	 * If you just have a different opinion on an interpretation you
	 * must create a new creation event that DOES NOT have the other
	 * event as predecessors. Applications will find these kind of
	 * disagreements by comparing the events.
	 * @return all creation events that are "corrected" by the current one
	 */
	public Set<ICreationEvent> getPredecessors();
	public void setPredecessors(Set<ICreationEvent> predecessors);
	public abstract void setInterpretationCreator(IActor interpretationCreator);
	public abstract IActor getInterpretationCreator();
}
