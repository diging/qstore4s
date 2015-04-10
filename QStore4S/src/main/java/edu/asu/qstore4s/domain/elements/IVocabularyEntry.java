package edu.asu.qstore4s.domain.elements;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.asu.qstore4s.domain.elements.impl.VocabularyEntry;


/**
 * This is the interface class for VocabularyEntry class.
 *
 */
@XmlJavaTypeAdapter(VocabularyEntry.Adapter.class)
public interface IVocabularyEntry extends IElement {

	public String getTerm();
	public void setTerm(String term);
	public abstract void setSourceURI(String sourceURI);
	public abstract String getSourceURI();
}
