package com.wheejuni.webserver.utils;

import java.util.HashMap;
import java.util.Map;

public class HTTPUtils {

    public static Map<String, String> parseRequestLine(String requestLine) {
        String[] elements = requestLine.split(" ");

        Map<String, String> requestLineProperties = new HashMap<>();

        requestLineProperties.put("method", elements[0]);
        requestLineProperties.put("URI", elements[1]);
        requestLineProperties.put("http-version", elements[2]);

        return requestLineProperties;
    }
}
