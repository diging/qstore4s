package edu.asu.qstore4s.async.manager.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.async.QueryInformation;
import edu.asu.qstore4s.async.manager.IAsyncQueryManager;

/**
 * 
 * This class is used for handling asynchronous query executions
 *
 */
@Service
public class AsyncQueryManager implements IAsyncQueryManager {

    // This map stores all the information about the query
    private Map<String, QueryInformation> queryStatusMap = new ConcurrentHashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isQueryRunning(String queryID) {
        QueryInformation info = queryStatusMap.get(queryID);
        return info != null ? info.isQueryRunning() : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setQueryStatus(String queryID, boolean status) {
        QueryInformation info = queryStatusMap.get(queryID);
        if (info != null) {
            info.setStatus(status);
        } else {
            queryStatusMap.put(queryID, new QueryInformation(status, queryID, ""));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setQueryResult(String queryID, String res) {
        QueryInformation info = queryStatusMap.get(queryID);
        info.setResult(res);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getQueryResult(String queryID) {
        QueryInformation info = queryStatusMap.get(queryID);
        return info != null ? info.getResult() : "";
    }
}
