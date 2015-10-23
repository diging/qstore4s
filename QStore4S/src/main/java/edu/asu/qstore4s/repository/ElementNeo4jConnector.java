package edu.asu.qstore4s.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Rachita Satyasi
 * Connector to QStore to get the total number of nodes
 */

@Service
public class ElementNeo4jConnector implements IElementNeo4jConnector {

    @Autowired
    private Neo4jTemplate template;

    /*
     * (non-Javadoc)
     * 
     * @see edu.asu.qstore4s.repository.IElement#getElementCount()
     */
    @Override
    public int getElementCount() {

        Map<String, Object> params = new HashMap<String, Object>();
        Result<Map<String, Object>> result = template.query(
                "match n return count(n)", params);
        Map<String, Object> count = result.single();
        return (Integer) count.get("count(n)");

    }

}
