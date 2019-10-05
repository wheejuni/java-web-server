package com.wheejuni.webserver.http;

public class RequestLine {
    private String line;
    private RequestTypes requestType;

    public RequestLine(String line) {
        this.line = line;
    }
}
