package com.wheejuni.webserver.http;

import com.wheejuni.webserver.utils.HTTPUtils;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HttpHeaders extends LinkedHashMap<String, String> implements Serializable, Map<String, String> {
    public Map<String, String> requestHeaderAttributes;

    public static final String ACCEPT = "Accept";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_LANGUAGE = "Content-Language";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String COOKIE = "Cookie";
    public static final String USER_AGENT = "User-Agent";
    public static final String EXPIRES = "Expires";
    public static final String HOST = "Host";

    public HttpHeaders(String headerString) {
        super(HTTPUtils.parseRequestHeader(headerString));
        this.requestHeaderAttributes = HTTPUtils.parseRequestHeader(headerString);
    }

    public int getContentLength() {
        return Integer.parseInt(this.requestHeaderAttributes.get(CONTENT_LENGTH));
    }
}
