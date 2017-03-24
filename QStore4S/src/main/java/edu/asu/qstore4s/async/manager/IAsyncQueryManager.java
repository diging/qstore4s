package edu.asu.qstore4s.async.manager;

import java.util.concurrent.ExecutionException;

import edu.asu.qstore4s.async.ExecutionStatus;

public interface IAsyncQueryManager {

    /**
     * Tells if a particular query is executing, failed or completed
     * 
     * @param queryID
     * @return
     * @throws ExecutionException
     */
    ExecutionStatus getQueryStatus(Integer queryID) throws ExecutionException;

    /**
     * Set the status of a query
     * 
     * @param queryID
     * @param status
     * @throws ExecutionException 
     */
    void setQueryStatus(Integer queryID, ExecutionStatus status) throws ExecutionException;

    /**
     * Set the result of a query
     * 
     * @param queryID
     * @param res
     * @throws ExecutionException 
     */
    void setQueryResult(Integer queryID, String res) throws ExecutionException;

    /**
     * Get the result of a query
     * 
     * @param queryID
     * @return
     * @throws ExecutionException 
     */
    String getQueryResult(Integer queryID) throws ExecutionException;

}
