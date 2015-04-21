package edu.asu.qstore4s.domain.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.factory.ITermPartsFactory;
import edu.asu.qstore4s.domain.elements.impl.TermParts;

/**
 * This is the factory class for TermParts element. 
 * This is used to instantiate TermParts class.
 * @author Veena Borannagowda
 *
 */
@Service
public class TermPartsFactory implements ITermPartsFactory {
	@Override
	public TermParts createTermParts()
	{
		return new TermParts();
	}
}
