package edu.asu.qstore4s.domain.elements;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.data.neo4j.annotation.NodeEntity;

import edu.asu.qstore4s.domain.elements.impl.Relation;
import edu.asu.qstore4s.domain.events.IAppellationEvent;
import edu.asu.qstore4s.domain.events.ICreationEvent;


/**
 * This is the interface class for Relation class.
 *
 */

@NodeEntity
@XmlJavaTypeAdapter(Relation.Adapter.class)
public interface IRelation extends IElement {

	public ICreationEvent getSubject();
	public void setSubject(ICreationEvent subject);
	
	
	public ICreationEvent getObject();
	public void setObject(ICreationEvent object);
	
	public IAppellationEvent getPredicate();
	public void setPredicate(IAppellationEvent predicate);
	ISourceReference getSourceReference();
	void setSourceReference(ISourceReference reference);
}
