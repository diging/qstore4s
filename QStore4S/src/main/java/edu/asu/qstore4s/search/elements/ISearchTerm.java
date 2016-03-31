package edu.asu.qstore4s.search.elements;

/**
 * 
 * @author Bhargav Desai
 *
 */

public interface ISearchTerm extends ISearchElement{

	public ISearchConcept getInterpretation() ;

	public void setInterpretation(ISearchConcept concept) ;

	public ISearchVocabularyEntry getNormalizedRepresentation() ;
		
	public void setNormalizedRepresentation(ISearchVocabularyEntry entry) ;

	public ISearchTermParts getPrintedRepresentation() ;
	public void setPrintedRepresentation(ISearchTermParts parts) ;

	
	public Boolean getCertain() ;

	
	public void setCertain(Boolean certainty) ;

	
	public ISearchSourceReference getSourceReference() ;

	public void setSourceReference(ISearchSourceReference reference);

	String getDatatype();
	void setDatatype(String datatype);	
	
}
