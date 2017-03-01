package edu.asu.qstore4s.async.manager;

public interface IAsyncQueryManager {

    Boolean isQueryRunning(String queryID);

    void setQueryStatus(String queryID, boolean status);

    void setQueryResult(String queryID, String res);

    String getQueryResult(String queryID);

}
