package edu.asu.qstore4s.async;

public class QueryInformation {
    private boolean status;
    private String id;
    private String result;

    public boolean isQueryRunning() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public QueryInformation(boolean status, String id, String result) {
        this.status = status;
        this.id = id;
        this.result = result;
    }

    @Override
    public String toString() {
        return "QueryInformation [status=" + status + ", id=" + id + ", result=" + result + "]";
    }

}
