package edu.asu.qstore4s.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import edu.asu.qstore4s.domain.elements.impl.Relation;

public interface RelationRepository extends GraphRepository<Relation> {
	
	
	/**
	 * method finds relation from id from neo4j database.
	 * @param id
	 * @return
	 */
	Relation findById(String id);
}
