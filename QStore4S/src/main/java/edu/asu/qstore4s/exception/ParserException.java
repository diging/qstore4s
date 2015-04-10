package edu.asu.qstore4s.exception;


/**
 * @author Bhargav Desai
 * This is exception class which is used to throw different parsing exception of XML provide by user. 
 */

public class ParserException extends Exception {

	
	
	private static final long serialVersionUID = 2957019437292932505L;
	
	public ParserException(){super();}
	
	public ParserException(String exception){super(exception);}
	

}
