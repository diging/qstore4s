package edu.asu.qstore4s.db.neo4j;
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
 *This class is used to get node information from the database.
 */


public interface IDbConnector {

/**
 * This method is used to call database to get information of relation or appellation event.
 * @param id
 * @return
 * @throws InvalidDataException
 */
	IElement get(String id) throws InvalidDataException;


	/**
	 * This method is used to get parent relation event from appellation event.
	 * @param creationEventList
	 * @return
	 * @throws URISyntaxException
	 * @throws InvalidDataException
	 */
	
List<ICreationEvent> searchRelationInDb(List<IElement> creationEventList)
		throws URISyntaxException, InvalidDataException;



/**
 * The method returns information of multiple relation or appellation event from list of ids. 
 * @param idList
 * @return
 * @throws InvalidDataException
 */

List<ICreationEvent> get(List<String> idList) throws InvalidDataException;


/**
 * The method search appellation or relation event from the database using given parameters.
 * @param queryObject
 * @return
 * @throws InvalidDataException
 */

List<ICreationEvent> searchFromDb(ISearchCreationEvent queryObject)
		throws InvalidDataException;



}
