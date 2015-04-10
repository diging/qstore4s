package edu.asu.qstore4s.domain.elements.factory;

import edu.asu.qstore4s.domain.elements.IConcept;

/**
 * This is the interface class for ConceptFactory class
 * which has the following methods:
 * createConcept()
 * @author Veena Borannagowda
 *
 */
public interface IConceptFactory {

	IConcept createConcept();

	IConcept createConcept(String sourceUri);

}
