package edu.asu.qstore4s.domain.elements;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.asu.qstore4s.domain.elements.impl.Concept;


/**
 * This is the interface class for Concept class.
 *
 */
@XmlJavaTypeAdapter(Concept.Adapter.class)
public interface IConcept extends IElement {

	//@SearchFieldMetadata(fieldname=ConceptFieldnames.SOURCEURI)
	public String getSourceURI();
	
	public void setSourceURI(String sourceURI);
	
}
