package edu.asu.qstore4s.repository;

/**
 * 
 * @author Rachita Satyasi Connector to QStore to get the total number of nodes
 */

public interface IElementNeo4jConnector {

    public abstract int getElementCount();

}