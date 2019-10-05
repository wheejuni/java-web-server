package com.wheejuni.webserver.utils;

import java.util.HashMap;
import java.util.Map;

public class HTTPUtils {
    private static final String HTTP_HEADER_DELIMITER = "\n";
    private static final String HTTP_HEADER_ATTRIBUTE_ROW_CHARACTER = ": ";

    public static Map<String, String> parseRequestLine(String requestLine) {
        String[] elements = requestLine.split(" ");

        Map<String, String> requestLineProperties = new HashMap<>();

        requestLineProperties.put("method", elements[0]);
        requestLineProperties.put("URI", elements[1]);
        requestLineProperties.put("http-version", elements[2]);

        return requestLineProperties;
    }

    public static Map<String, String> parseRequestHeader(String requestHeaderString) {
        Map<String, String> requestHeaders = new HashMap<>();

        String[] entries = requestHeaderString.split(HTTP_HEADER_DELIMITER);

        for(String entry: entries) {
            String[] delimitedEntry = entry.split(HTTP_HEADER_ATTRIBUTE_ROW_CHARACTER);
            requestHeaders.put(delimitedEntry[0], delimitedEntry[1]);
        }

        return requestHeaders;
    }


}
