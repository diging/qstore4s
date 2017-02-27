package edu.asu.qstore4s.message;

public class Message {
    private static final String RETURN_JSON = "application/json";

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message(String message) {
        this.message = message;
    }

    public String toString(String type) {
        if (type != null && type.equals(RETURN_JSON)) {
            return "{\"message\" = \"" + message + "\" }";
        }
        return "<message>" + message + "</message>";
    }

}
