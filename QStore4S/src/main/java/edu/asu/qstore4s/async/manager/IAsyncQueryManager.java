package edu.asu.qstore4s.async.manager;

public interface IAsyncQueryManager {

    /**
     * Tells if a particular query is executing or not
     * @param queryID
     * @return
     */
    Boolean isQueryRunning(String queryID);

    /**
     * Set the status of a query
     * @param queryID
     * @param status
     */
    void setQueryStatus(String queryID, boolean status);

    /**
     * Set the result of a query
     * @param queryID
     * @param res
     */
    void setQueryResult(String queryID, String res);

    /**
     * Get the result of a query
     * @param queryID
     * @return
     */
    String getQueryResult(String queryID);

}
