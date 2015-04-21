package edu.asu.qstore4s.domain.elements.factory;

import edu.asu.qstore4s.domain.elements.impl.SourceReference;


/**
 * This is the interface class for SourceReferenceFactory class
 * which has the following methods:
 * createSourceReference()
 * @author Veena Borannagowda
 *
 */
public interface ISourceReferenceFactory {

	SourceReference createSourceReference();

	SourceReference createSourceReference(String sourceUri);

}
