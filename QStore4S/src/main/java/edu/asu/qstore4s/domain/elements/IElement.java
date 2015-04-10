package edu.asu.qstore4s.domain.elements;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.asu.qstore4s.domain.elements.impl.Element;


/**
 * This is the interface class for Element class.
 *
 */


@XmlJavaTypeAdapter(Element.Adapter.class)
public interface IElement {
	public String getId();
	public void setId(String id);
		
	public IActor getCreator();
	public void setCreator(IActor actor);
	
	public Date getCreationDate();
	public void setCreationDate(Date date);
	
	public IPlace getCreationPlace();
	public void setCreationPlace(IPlace place);
	String getInternal_refId();
	void setInternal_refId(String internal_refId);
	String getExternal_refId();
	void setExternal_refId(String external_refId);
	void setRefId(String refId);
	String getRefId();
	boolean isIdAssigned();
	void setIdAssigned(boolean isIdAssigned);
}
