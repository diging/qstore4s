package edu.asu.qstore4s.search.events;

import edu.asu.qstore4s.search.elements.ISearchActor;
import edu.asu.qstore4s.search.elements.ISearchElement;
import edu.asu.qstore4s.search.elements.ISearchSourceReference;

/**
 * 
 * @author Bhargav Desai
 *
 */


public interface ISearchCreationEvent extends ISearchElement{

	ISearchSourceReference getSourceReference();

	void setSourceReference(ISearchSourceReference reference);

	ISearchActor getInterpretationCreator();

	void setInterpretationCreator(ISearchActor interpretationCreator);

}
