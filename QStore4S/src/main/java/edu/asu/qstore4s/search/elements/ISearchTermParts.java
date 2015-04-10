package edu.asu.qstore4s.search.elements;

import java.util.Set;

/**
 * 
 * @author Bhargav Desai
 *
 */

public interface ISearchTermParts extends ISearchElement {


	void setSearchTermParts(Set<ISearchTermPart> termPartList);

	Set<ISearchTermPart> getSearchTermParts();

	ISearchSourceReference getReferencedSource();

	void setReferencedSource(ISearchSourceReference reference);


	

}
