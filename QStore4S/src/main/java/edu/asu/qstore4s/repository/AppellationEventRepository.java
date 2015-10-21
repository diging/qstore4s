package edu.asu.qstore4s.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.qstore4s.domain.events.impl.AppellationEvent;
import edu.asu.qstore4s.domain.events.impl.RelationEvent;

public interface AppellationEventRepository extends
        GraphRepository<AppellationEvent> {

    AppellationEvent findById(String id);

    @Override
    @Transactional
    public <U extends AppellationEvent> U save(U arg0);

    /**
     * The method find parent relation event from appellation event
     * 
     * @param appellationEvent
     * @return
     */

    @Query("start result = node({0}) "
            + "match (result)<-[hasSubject]-(relation)<-[hasRelation]-(relationEvent) "
            + "return relationEvent limit 10")
    public List<RelationEvent> getRelationfromId(
            AppellationEvent appellationEvent);

    @Query("match (node:AppellationEvent) return count(node)")
    public int getAppellationEvenCount();

}