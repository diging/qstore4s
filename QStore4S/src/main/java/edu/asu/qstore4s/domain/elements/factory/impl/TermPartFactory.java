package edu.asu.qstore4s.domain.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.factory.ITermPartFactory;
import edu.asu.qstore4s.domain.elements.impl.TermPart;

/**
 * This is the factory class for TermPart element. 
 * This is used to instantiate TermPart class.
 * @author Veena Borannagowda
 *
 */
@Service
public class TermPartFactory implements ITermPartFactory {
	@Override
	public TermPart createTermPart()
	{
		return new TermPart();
	}
}
