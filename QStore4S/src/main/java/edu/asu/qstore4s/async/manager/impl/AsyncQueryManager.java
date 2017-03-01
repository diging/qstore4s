package edu.asu.qstore4s.async.manager.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.async.QueryInformation;
import edu.asu.qstore4s.async.manager.IAsyncQueryManager;

@Service
public class AsyncQueryManager implements IAsyncQueryManager {

    private Map<String, QueryInformation> queryStatusMap = new ConcurrentHashMap<>();

    @Override
    public Boolean isQueryRunning(String queryID) {
        QueryInformation info = queryStatusMap.get(queryID);
        return info != null ? info.isQueryRunning() : false;
    }

    @Override
    public void setQueryStatus(String queryID, boolean status) {
        QueryInformation info = queryStatusMap.get(queryID);
        if (info != null) {
            info.setStatus(status);
        } else {
            queryStatusMap.put(queryID, new QueryInformation(status, queryID, ""));
        }

    }

    @Override
    public void setQueryResult(String queryID, String res) {
        QueryInformation info = queryStatusMap.get(queryID);
        info.setResult(res);
    }

    @Override
    public String getQueryResult(String queryID) {
        QueryInformation info = queryStatusMap.get(queryID);
        return info != null ? info.getResult() : "";
    }
}
