package edu.asu.qstore4s.search.elements.impl;

import org.springframework.data.neo4j.annotation.GraphProperty;

import edu.asu.qstore4s.search.elements.ISearchExpression;
import edu.asu.qstore4s.search.elements.ISearchFormat;
import edu.asu.qstore4s.search.elements.ISearchFormattedPointer;
import edu.asu.qstore4s.search.elements.ISearchSourceReference;
import edu.asu.qstore4s.search.elements.ISearchTermPart;
import edu.asu.qstore4s.search.elements.ISearchVocabularyEntry;
/**
 * This file contains the definition of SearchTermPart class. 
 * @author Bhargav Desai
 *
 */

public class SearchTermPart extends SearchElement implements ISearchTermPart {
	
	
	private  ISearchExpression expression;
	private ISearchFormat format;
	private ISearchFormattedPointer formattedPointer;
	 
	
	private int position;
	
	private ISearchVocabularyEntry normalization;

	
	@GraphProperty
	private ISearchSourceReference source_reference;
	
	@Override
	public int getPosition() {
		return position;
	}
	
	@Override
	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public void setNormalization(ISearchVocabularyEntry normalization) {
		this.normalization = normalization;
	}

	@Override
	public ISearchVocabularyEntry getNormalization() {
		return normalization;
	}

	
	@Override
	public ISearchSourceReference getSourceReference() {
		return source_reference;
	}

	@Override
	public void setSourceReference(ISearchSourceReference reference) {
		this.source_reference = reference;
	}


	
	
	
		@Override
	public ISearchExpression getSearchExpression() {
		return expression;
	}



	@Override
	public void setSearchExpression(ISearchExpression expression) {
		this.expression = expression;
	}


	@Override
	public ISearchFormat getSearchFormat() {
		return format;
	}


	@Override
	public void setSearchFormat(ISearchFormat format) {
		this.format = format;
	}


	@Override
	public ISearchFormattedPointer getSearchFormattedPointer() {
		return formattedPointer;
	}


	@Override
	public void setSearchFormattedPointer(ISearchFormattedPointer formattedPointer) {
		this.formattedPointer = formattedPointer;
	}

	



	

}
