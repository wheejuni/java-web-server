package com.wheejuni.webserver.http.exceptions;

public class NoMatchingRequestTypeException extends RuntimeException {

    public NoMatchingRequestTypeException(String message) {
        super(message);
    }

    public NoMatchingRequestTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMatchingRequestTypeException() {

    }
}
