package edu.asu.qstore4s.domain.events;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.data.neo4j.annotation.NodeEntity;

import edu.asu.qstore4s.domain.elements.IActor;
import edu.asu.qstore4s.domain.elements.IRelation;
import edu.asu.qstore4s.domain.events.impl.RelationEvent;

@XmlJavaTypeAdapter(RelationEvent.Adapter.class)
@NodeEntity
public interface IRelationEvent extends ICreationEvent {

	public IRelation getRelation();
	public void setRelation(IRelation relation);
	
	/**
	 * Creator of statement (e.g. A says that -> A is relation creator)
	 * @return
	 */
	public IActor getRelationCreator();
	public void setRelationCreator(IActor actor);
	
	
}
