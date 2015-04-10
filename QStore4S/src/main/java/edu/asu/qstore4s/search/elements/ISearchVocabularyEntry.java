package edu.asu.qstore4s.search.elements;

/**
 * 
 * @author Bhargav Desai
 *
 */

public interface ISearchVocabularyEntry extends ISearchElement {

	String getTerm();

	void setTerm(String term);

	String getSourceURI();

	void setSourceURI(String sourceURI);


}
