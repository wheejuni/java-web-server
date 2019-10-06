package com.wheejuni.webserver.http.request;

import com.wheejuni.webserver.utils.HTTPUtils;

import java.util.Map;

public class RequestLine {
    private String httpVersion;
    private String uri;
    private RequestMethods requestType;

    public RequestLine(String line) {
        System.out.println(line);
        Map<String, String> requestProperties = HTTPUtils.parseRequestLine(line);

        this.uri = requestProperties.get("URI");
        this.httpVersion = requestProperties.get("http-version");
        this.requestType = RequestMethods.getByLineString(requestProperties.get("method"));
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public String getUri() {
        return uri;
    }

    public RequestMethods getRequestType() {
        return requestType;
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
