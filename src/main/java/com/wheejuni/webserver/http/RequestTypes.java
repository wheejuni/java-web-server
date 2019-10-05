package com.wheejuni.webserver.http;

import com.wheejuni.webserver.http.exceptions.NoMatchingRequestTypeException;

import java.util.Arrays;

public enum RequestTypes {

    GET,
    POST,
    PUT,
    PATCH,
    DELETE;

    public static RequestTypes getByLineString(final String lineString) {
        return Arrays.stream(RequestTypes.values())
                .filter(t -> t.name()
                        .equalsIgnoreCase(lineString)).findFirst().orElseThrow(NoMatchingRequestTypeException::new);
    }
}
