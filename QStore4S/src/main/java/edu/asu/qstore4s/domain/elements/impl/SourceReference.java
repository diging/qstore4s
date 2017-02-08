package edu.asu.qstore4s.domain.elements.impl;

import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.ogm.annotation.GraphId;

/**
 * This file contains the definition of SourceReference class.
 *
 */
@XmlRootElement
public class SourceReference extends Element {

    @GraphId
    Long graphId;
    
    private String sourceURI;

    public SourceReference() {
        sourceURI = "";
    }

    public String getSourceURI() {
        return sourceURI;
    }

    public void setSourceURI(String uri) {
        this.sourceURI = uri;
    }
    
    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

}
