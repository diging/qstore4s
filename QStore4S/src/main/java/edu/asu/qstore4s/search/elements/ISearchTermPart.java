package edu.asu.qstore4s.search.elements;

/**
 * 
 * @author Bhargav Desai
 *
 */

public interface ISearchTermPart extends ISearchElement {



	ISearchExpression getSearchExpression();

	void setSearchExpression(ISearchExpression expression);

	ISearchFormat getSearchFormat();


	ISearchFormattedPointer getSearchFormattedPointer();


	void setSearchFormat(ISearchFormat createSearchFormat);


	public int getPosition() ;
	
	public void setPosition(int position) ;

	public void setNormalization(ISearchVocabularyEntry normalization) ;

	public ISearchVocabularyEntry getNormalization() ;

	
	public ISearchSourceReference getSourceReference() ;

	public void setSourceReference(ISearchSourceReference reference) ;

	void setSearchFormattedPointer(ISearchFormattedPointer formattedPointer);

	
	
	
	

}
