package com.wheejuni.webserver.http.request;

import java.util.List;

public interface RequestMapperPredicate {
    boolean isMatchingRequest(String path, List<RequestMethods> methods);
}
