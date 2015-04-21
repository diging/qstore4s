package edu.asu.qstore4s.domain.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.domain.elements.factory.ITermFactory;
import edu.asu.qstore4s.domain.elements.impl.Term;

/**
 * This is the factory class for Term element. 
 * This is used to instantiate Term class.
 * @author Veena Borannagowda
 *
 */
@Service
public class TermFactory implements ITermFactory {
@Override
	public Term createTerm()
	{
		return new Term();
	}
}
