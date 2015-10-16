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
 *
 */

@Service
public class Element {
	
	@Autowired
	private Neo4jTemplate template;
	
    public int getElementCount()
    {
    
    	Map<String, Object> params = new HashMap<String, Object>();
    	Result<Map<String,Object>> result=template.query("match n return count(n)", params);
    	Map<String,Object> count=result.single();
    	Object value=count.get("count(n)");
     	return ((Integer)value).intValue();
  
    }

}
