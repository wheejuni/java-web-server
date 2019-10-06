package com.wheejuni.webserver.http.request;

import com.wheejuni.webserver.http.exceptions.NoMatchingRequestTypeException;

import java.util.Arrays;

public enum RequestMethods {

    GET,
    POST,
    PUT,
    PATCH,
    DELETE,
    OPTIONS;

    public static RequestMethods getByLineString(final String lineString) {
        return Arrays.stream(RequestMethods.values())
                .filter(t -> t.name()
                        .equalsIgnoreCase(lineString)).findFirst().orElseThrow(NoMatchingRequestTypeException::new);
    }
}
