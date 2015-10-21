package edu.asu.qstore4s.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.qstore4s.domain.events.impl.RelationEvent;
import org.springframework.data.neo4j.annotation.Query;

public interface RelationEventRepository extends GraphRepository<RelationEvent> {
    RelationEvent findById(String id);

    /**
     * {@inheritDoc}
     */

    @Override
    @Transactional
    public <U extends RelationEvent> Iterable<U> save(Iterable<U> arg0);

    @Query("match (node:RelationEvent) return count(node)")
    public int getRelationEvenCount();
}
