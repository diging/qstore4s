package edu.asu.qstore4s.domain.elements.factory;

import edu.asu.qstore4s.domain.elements.impl.Concept;


/**
 * This is the interface class for ConceptFactory class
 * which has the following methods:
 * createConcept()
 * @author Veena Borannagowda
 *
 */
public interface IConceptFactory {

	Concept createConcept();

	Concept createConcept(String sourceUri);

}
