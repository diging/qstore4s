package edu.asu.qstore4s.async;

public class QueryInformation {
    private ExecutionStatus status;
    private Integer id;
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public QueryInformation(ExecutionStatus status, Integer id, String result) {
        this.status = status;
        this.id = id;
        this.result = result;
    }

    @Override
    public String toString() {
        return "QueryInformation [status=" + status + ", id=" + id + ", result=" + result + "]";
    }

}
