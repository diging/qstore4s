package edu.asu.qstore4s.domain.elements;

import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.data.neo4j.annotation.NodeEntity;

import edu.asu.qstore4s.domain.elements.impl.Term;


/**
 * This is the interface class for Term class.
 *
 */
@XmlJavaTypeAdapter(Term.Adapter.class)
@NodeEntity
public interface ITerm extends IElement {

	public IConcept getInterpretation();
	public void setInterpretation(IConcept concept);
	
	public IVocabularyEntry getNormalizedRepresentation();
	public void setNormalizedRepresentation(IVocabularyEntry entry);
	
	public ITermParts getPrintedRepresentation();
	public void setPrintedRepresentation(ITermParts parts);
	
	public boolean isCertain();
	public void setIsCertain(boolean certainty);
	
	public Set<ITerm> getReferencedTerms();
	public void setReferencedTerms(Set<ITerm> terms);
	ISourceReference getSourceReference();
	void setSourceReference(ISourceReference reference);
	
}
