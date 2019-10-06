package com.wheejuni.webserver.http.request;

import com.wheejuni.webserver.listener.RequestListener;

import java.util.List;

public class RequestMapping implements RequestMapperPredicate {

    private String path;
    private RequestMethods method;

    private RequestMapping(String path, RequestMethods method) {
        this.path = path;
        this.method = method;
    }

    public static RequestMapping ofRequestLine(RequestLine line) {
        return new RequestMapping(line.getUri(), line.getRequestType());
    }

    @Override
    public boolean isMatchingRequest(String path, List<RequestMethods> methods) {
        return path.equals(this.path) && methods.contains(this.method);
    }
}
