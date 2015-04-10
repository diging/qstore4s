package edu.asu.qstore4s.search.elements.impl;

import edu.asu.qstore4s.search.elements.ISearchVocabularyEntry;
/**
 * This file contains the definition of SearchVocabularyEntry class.
 * @author Bhargav Desai
 *
 */

public class SearchVocabularyEntry extends SearchElement implements ISearchVocabularyEntry{
	
	private String term;
	private String sourceURI;

	@Override
	public String getTerm() {
		return term;
	}

	@Override
	public void setTerm(String term) {
		this.term = term;
	}
	
	
	@Override
	public String getSourceURI() {
		return sourceURI;
	}

	@Override
	public void setSourceURI(String sourceURI) {
		this.sourceURI = sourceURI;
	}

	
	
}
