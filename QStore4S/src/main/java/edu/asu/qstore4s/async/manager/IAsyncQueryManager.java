package edu.asu.qstore4s.async.manager;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;

import edu.asu.qstore4s.async.ExecutionStatus;
import edu.asu.qstore4s.domain.events.impl.CreationEvent;

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
    void setQueryResult(Integer queryID, List<CreationEvent> res) throws ExecutionException;

    /**
     * Get the result of a query
     * 
     * @param queryID
     * @param accept
     * @return
     * @throws ExecutionException
     * @throws JSONException
     */
    String getQueryResult(Integer queryID, String accept) throws ExecutionException, JSONException;

}
