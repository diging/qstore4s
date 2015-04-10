package edu.asu.qstore4s.domain.elements;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.asu.qstore4s.domain.elements.impl.SourceReference;


/**
 * This is the interface class for SourceReference class.
 *
 */
@XmlJavaTypeAdapter(SourceReference.Adapter.class)
public interface ISourceReference extends IElement {

	public String getSourceURI();
	public void setSourceURI(String uri);
}
