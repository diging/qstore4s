package edu.asu.qstore4s.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import edu.asu.qstore4s.domain.elements.impl.TermPart;

public interface TermPartRepository extends GraphRepository<TermPart>{
	
	
	/**
	 * method finds TermPart from id from neo4j database.
	 * @param id
	 * @return
	 */

	TermPart findById(String id);
}
