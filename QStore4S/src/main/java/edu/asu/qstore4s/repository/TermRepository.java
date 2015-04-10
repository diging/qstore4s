package edu.asu.qstore4s.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import edu.asu.qstore4s.domain.elements.impl.Term;

public interface TermRepository extends GraphRepository<Term>{
	
	
	/**
	 * method finds Term from id from neo4j database.
	 * @param id
	 * @return
	 */

	Term findById(String id);
}
