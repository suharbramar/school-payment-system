package io.bramcode.movie.moviecategoryservices.exception;

public class CustomException extends RuntimeException{
    private String rootCause;
    private String message;
    private String details;

    public CustomException(){}

    public CustomException(String rootCause, String message, String details) {
        this.rootCause = rootCause;
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public CustomException setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public CustomException setDetails(String details) {
        this.details = details;
        return this;
    }

    public String getRootCause() {
        return rootCause;
    }

    public CustomException setRootCause(String rootCause) {
        this.rootCause = rootCause;
        return this;
    }
}
