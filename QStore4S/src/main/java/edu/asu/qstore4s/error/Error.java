package edu.asu.qstore4s.error;

public class Error {
    private static final String RETURN_JSON = "application/json";

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString(String type) {
        if (type != null && type.equals(RETURN_JSON)) {
            return "{\"errorMessage\" = \"" + errorMessage + "\" }";
        }
        return "<errorMessage>" + errorMessage + "</errorMessage>";
    }

}
