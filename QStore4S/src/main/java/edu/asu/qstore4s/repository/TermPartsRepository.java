package edu.asu.qstore4s.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import edu.asu.qstore4s.domain.elements.impl.TermParts;

public interface TermPartsRepository extends GraphRepository<TermParts>{
	
	
	/**
	 * method finds TermParts from id from neo4j database.
	 * @param id
	 * @return
	 */

	TermParts findById(String id);
}
