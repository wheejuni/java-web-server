package com.wheejuni.webserver.http;

import com.wheejuni.webserver.utils.HTTPUtils;

import java.util.Map;

public class RequestLine {
    private String httpVersion;
    private String uri;
    private RequestTypes requestType;

    public RequestLine(String line) {
        System.out.println(line);
        Map<String, String> requestProperties = HTTPUtils.parseRequestLine(line);

        this.uri = requestProperties.get("URI");
        this.httpVersion = requestProperties.get("http-version");
        this.requestType = RequestTypes.getByLineString(requestProperties.get("method"));
    }

    @Override
    public String toString() {
        return "RequestLine{" +
                "httpVersion='" + httpVersion + '\'' +
                ", uri='" + uri + '\'' +
                ", requestType=" + requestType +
                '}';
    }
}
