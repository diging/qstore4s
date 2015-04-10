package edu.asu.qstore4s.service;

import java.net.URISyntaxException;
import java.util.List;

import edu.asu.qstore4s.domain.elements.IElement;
import edu.asu.qstore4s.domain.events.ICreationEvent;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.search.events.ISearchCreationEvent;

/**
 * 
 * @author Bhargav Desai
 * 
 */

public interface IStoreManager {

	/**
	 * The method calls database dependent class to insert list of the object
	 * into the database.
	 * 
	 * @throws InvalidDataException
	 *             URISyntexException
	 */
	public List<ICreationEvent> insertIntoDb(List<List<IElement>> bean)
			throws URISyntaxException, InvalidDataException;

	/**
	 * The method calls database dependent class to getthe object from the
	 * database.
	 * 
	 * @param id
	 * @return
	 * @throws InvalidDataException
	 */
	IElement getObjectFromDb(String id) throws InvalidDataException;

	/**
	 * The method calls database dependent class to search list of the object
	 * from the database.
	 * 
	 * @param queryObject
	 * @return
	 * @throws InvalidDataException
	 */
	List<ICreationEvent> searchObjectFromDb(ISearchCreationEvent queryObject)
			throws InvalidDataException;

	/**
	 * The method calls database dependent class to search list of the relation
	 * object from the database.
	 * 
	 * @param creationEventList
	 * @return
	 * @throws URISyntaxException
	 * @throws InvalidDataException
	 */
	List<ICreationEvent> searchRelationFromDb(
			List<List<IElement>> creationEventList) throws URISyntaxException,
			InvalidDataException;

	/**
	 * The method calls database dependent class to insert list of the object
	 * from the database for particular id.
	 * 
	 * @throws InvalidDataException
	 */
	public List<ICreationEvent> getObjectFromDb(List<String> idList)
			throws InvalidDataException;

}
