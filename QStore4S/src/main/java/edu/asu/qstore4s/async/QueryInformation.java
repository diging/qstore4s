package edu.asu.qstore4s.async;

import java.util.List;

import edu.asu.qstore4s.domain.events.impl.CreationEvent;

public class QueryInformation {
    private ExecutionStatus status;
    private Integer id;
    private List<CreationEvent> result;

    public ExecutionStatus getQueryStatus() {
        return status;
    }

    public void setStatus(ExecutionStatus status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CreationEvent> getResult() {
        return result;
    }

    public void setResult(List<CreationEvent> result) {
        this.result = result;
    }

    public QueryInformation(ExecutionStatus status, Integer id, List<CreationEvent> result) {
        super();
        this.status = status;
        this.id = id;
        this.result = result;
    }

    @Override
    public String toString() {
        return "QueryInformation [status=" + status + ", id=" + id + ", result=" + result + "]";
    }

}
