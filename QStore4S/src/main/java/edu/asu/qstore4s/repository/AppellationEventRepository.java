package edu.asu.qstore4s.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.qstore4s.domain.events.impl.AppellationEvent;
import edu.asu.qstore4s.domain.events.impl.RelationEvent;

/**
 * Contains methods to return Relation Events that reference a Appellation Event
 * and also the count of Appellation Events
 * 
 * @author Rachita Satyasif
 *
 */

public interface AppellationEventRepository extends
        GraphRepository<AppellationEvent> {

    public AppellationEvent findById(String id);

    @Override
    @Transactional
    public <U extends AppellationEvent> U save(U arg0);

    /**
     * The method returns all Relation Events that reference the given
     * Appellation Event
     * 
     * @param appellationEvent
     * @return
     */
    @Query("start result = node({0}) "
            + "match (result)<-[hasSubject]-(relation)<-[hasRelation]-(relationEvent) "
            + "return relationEvent limit 10")
    public List<RelationEvent> getRelationfromId(
            AppellationEvent appellationEvent);

    /**
     * The method returns the count of Appellation Events
     * 
     */
    @Query("match (node:AppellationEvent) return count(node)")
    public int getAppellationEventCount();

}