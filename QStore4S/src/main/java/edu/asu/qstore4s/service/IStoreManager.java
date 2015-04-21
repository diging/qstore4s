package edu.asu.qstore4s.service;

import java.net.URISyntaxException;
import java.util.List;

import edu.asu.qstore4s.domain.elements.impl.Element;
import edu.asu.qstore4s.domain.events.impl.CreationEvent;
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
	public List<CreationEvent> insertIntoDb(List<List<Element>> bean)
			throws URISyntaxException, InvalidDataException;

	/**
	 * The method calls database dependent class to getthe object from the
	 * database.
	 * 
	 * @param id
	 * @return
	 * @throws InvalidDataException
	 */
	Element getObjectFromDb(String id) throws InvalidDataException;

	/**
	 * The method calls database dependent class to search list of the object
	 * from the database.
	 * 
	 * @param queryObject
	 * @return
	 * @throws InvalidDataException
	 */
	List<CreationEvent> searchObjectFromDb(ISearchCreationEvent queryObject)
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
	List<CreationEvent> searchRelationFromDb(
			List<List<Element>> creationEventList) throws URISyntaxException,
			InvalidDataException;

	/**
	 * The method calls database dependent class to insert list of the object
	 * from the database for particular id.
	 * 
	 * @throws InvalidDataException
	 */
	public List<CreationEvent> getObjectFromDb(List<String> idList)
			throws InvalidDataException;

}
