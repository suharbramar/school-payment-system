package sch.binadharma.spp.exception;

public class CustomExceptionSchema {
    private String rootCause;
    private String message;
    private String details;

    public CustomExceptionSchema(){}

    public CustomExceptionSchema(String rootCause, String message, String details) {
        this.rootCause = rootCause;
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public CustomExceptionSchema setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public CustomExceptionSchema setDetails(String details) {
        this.details = details;
        return this;
    }

    public String getRootCause() {
        return rootCause;
    }

    public CustomExceptionSchema setRootCause(String rootCause) {
        this.rootCause = rootCause;
        return this;
    }
}
