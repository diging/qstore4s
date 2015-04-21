package edu.asu.qstore4s.domain.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.factory.IElementFactory;
import edu.asu.qstore4s.domain.elements.impl.Element;

/**
 * This is the factory class for Element element. 
 * This is used to instantiate Element class.
 * @author Veena Borannagowda
 *
 */

@Service
public class ElementFactory implements IElementFactory {
@Override
	public Element createElement()
	{
		return new Element();
	}
}
