package edu.asu.qstore4s.db.neo4j;

import java.net.URISyntaxException;
import java.util.List;

import edu.asu.qstore4s.domain.elements.IElement;
import edu.asu.qstore4s.domain.events.ICreationEvent;
import edu.asu.qstore4s.exception.InvalidDataException;

/**
 * This file contains the interface class for StoreObjectsToDb
 * which has the following methods.
 * writeObjectsToDb
 * @author Veena Borannagowda
 *
 */

public interface IStoreObjectsToDb {
	
	
	/**
	 * The method insert appellation and relation objects into neo4j database.
	 * @param relation
	 * @return
	 * @throws URISyntaxException
	 * @throws InvalidDataException
	 */
	List<ICreationEvent> writeObjectsToDb(List<List<IElement>> relation) throws URISyntaxException, InvalidDataException;
}
