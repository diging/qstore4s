package edu.asu.qstore4s.domain.elements;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.data.neo4j.annotation.NodeEntity;

import edu.asu.qstore4s.domain.elements.impl.TermPart;


/**
 * This is the interface class for TermPart class.
 *
 */

@NodeEntity
@XmlJavaTypeAdapter(TermPart.Adapter.class)
public interface ITermPart extends IElement {

	public int getPosition();
	public void setPosition(int position);
	
	public String getExpression();
	public void setExpression(String expression);
	
	public IVocabularyEntry getNormalization();
	public void setNormalization(IVocabularyEntry entry);
    
	public void setFormattedPointer(String formattedPointer);
	public String getFormattedPointer();
	
	public void setFormat(String format);
	public String getFormat();
	ISourceReference getSourceReference();
	void setSourceReference(ISourceReference reference);
}
