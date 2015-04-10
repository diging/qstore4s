package edu.asu.qstore4s.domain.elements;

import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.data.neo4j.annotation.NodeEntity;

import edu.asu.qstore4s.domain.elements.impl.TermParts;


/**
 * This is the interface class for TermParts class.
 *
 */

@NodeEntity
@XmlJavaTypeAdapter(TermParts.Adapter.class)
public interface ITermParts extends IElement {

	
	public Set<ITermPart> getTermParts();
	public void setTermParts(Set<ITermPart> parts);
	
	public ISourceReference getReferencedSource();
	public void setReferencedSource(ISourceReference reference);
}
