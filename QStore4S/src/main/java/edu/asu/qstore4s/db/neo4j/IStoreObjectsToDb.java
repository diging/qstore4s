package edu.asu.qstore4s.db.neo4j;

import java.net.URISyntaxException;
import java.util.List;

import edu.asu.qstore4s.domain.elements.impl.Element;
import edu.asu.qstore4s.domain.events.impl.CreationEvent;
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
	List<CreationEvent> writeObjectsToDb(List<List<Element>> relation) throws URISyntaxException, InvalidDataException;
}
