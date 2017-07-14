package edu.asu.qstore4s.async.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import edu.asu.qstore4s.async.ExecutionStatus;
import edu.asu.qstore4s.async.QueryInformation;
import edu.asu.qstore4s.async.manager.IAsyncQueryManager;
import edu.asu.qstore4s.domain.events.impl.CreationEvent;

/**
 * 
 * This class is used for handling asynchronous query executions
 *
 */
@Service
public class AsyncQueryManager implements IAsyncQueryManager {

    // This map stores all the information about the query
    private Map<Integer, QueryInformation> queryStatusMap = new ConcurrentHashMap<>();

    // Caches the query information for a day
    private LoadingCache<Integer, Optional<QueryInformation>> queryStatusCache = CacheBuilder.newBuilder()
            .maximumSize(1000).expireAfterWrite(1, TimeUnit.DAYS)
            .removalListener(notification -> queryStatusMap.remove(notification.getKey()))
            .build(new CacheLoader<Integer, Optional<QueryInformation>>() {
                public Optional<QueryInformation> load(Integer key) throws Exception {
                    return Optional.ofNullable(queryStatusMap.get(key));
                }
            });

    /**
     * {@inheritDoc}
     * 
     * @throws ExecutionException
     */
    @Override
    public ExecutionStatus getQueryStatus(Integer queryID) throws ExecutionException {
        Optional<QueryInformation> info = queryStatusCache.get(queryID);
        return info.isPresent() ? info.get().getQueryStatus() : ExecutionStatus.UNKNOWN;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ExecutionException
     */
    @Override
    public void setQueryStatus(Integer queryID, ExecutionStatus status) throws ExecutionException {
        Optional<QueryInformation> info = queryStatusCache.get(queryID);
        if (info.isPresent()) {
            info.get().setStatus(status);
        } else {
            queryStatusCache.put(queryID,
                    Optional.ofNullable(new QueryInformation(status, queryID, new ArrayList<CreationEvent>())));
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @throws ExecutionException
     */
    @Override
    public void setQueryResult(Integer queryID, List<CreationEvent> res) throws ExecutionException {
        Optional<QueryInformation> info = queryStatusCache.get(queryID);
        info.get().setResult(res);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ExecutionException
     * @throws JSONException
     */
    @Override
    public List<CreationEvent> getQueryResult(Integer queryID) throws ExecutionException, JSONException {
        Optional<QueryInformation> info = queryStatusCache.get(queryID);

        if (info.isPresent()) {
            return info.get().getResult();
        }
        return new ArrayList<>();
    }

}
