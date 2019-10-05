package com.wheejuni.webserver.utils

import spock.lang.Specification

class HTTPUtilsTest extends Specification {

    def "HTTP 요청 라인을 파싱해서, elements로 변환한다"() {
        given:
        String requestLine = "GET /hi HTTP/1.1"

        when:
        Map<String, String> parsedElements = HTTPUtils.parseRequestLine(requestLine)

        then:
        parsedElements.get("method") == "GET"
        parsedElements.get("URI") == "/hi"
        parsedElements.get("http-version") == "HTTP/1.1"

    }
}
