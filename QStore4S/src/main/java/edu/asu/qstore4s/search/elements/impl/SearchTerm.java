package edu.asu.qstore4s.search.elements.impl;

import edu.asu.qstore4s.search.elements.ISearchConcept;
import edu.asu.qstore4s.search.elements.ISearchSourceReference;
import edu.asu.qstore4s.search.elements.ISearchTerm;
import edu.asu.qstore4s.search.elements.ISearchTermParts;
import edu.asu.qstore4s.search.elements.ISearchVocabularyEntry;

/**
 * This file contains the definition of SearchTerm class.
 * 
 * @author Bhargav Desai
 *
 */

public class SearchTerm extends SearchElement implements ISearchTerm {

    private ISearchConcept interpretation;

    private ISearchVocabularyEntry normalized_representation;

    private ISearchTermParts printedRepresentation;

    private Boolean certain;

    private ISearchSourceReference source_reference;

    private String datatype;

    @Override
    public ISearchConcept getInterpretation() {
        return interpretation;
    }

    @Override
    public void setInterpretation(ISearchConcept concept) {
        this.interpretation = concept;
    }

    @Override
    public ISearchVocabularyEntry getNormalizedRepresentation() {
        return normalized_representation;
    }

    @Override
    public void setNormalizedRepresentation(ISearchVocabularyEntry entry) {
        this.normalized_representation = entry;
    }

    @Override
    public ISearchTermParts getPrintedRepresentation() {
        return printedRepresentation;
    }

    @Override
    public void setPrintedRepresentation(ISearchTermParts parts) {
        this.printedRepresentation = parts;
    }

    @Override
    public Boolean getCertain() {
        return certain;
    }

    @Override
    public void setCertain(Boolean certainty) {
        this.certain = certainty;
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
    public String getDatatype() {
        return datatype;
    }

    @Override
    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

}
