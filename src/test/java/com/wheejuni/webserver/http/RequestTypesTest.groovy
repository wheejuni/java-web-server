package com.wheejuni.webserver.http

import com.wheejuni.webserver.http.request.RequestMethods
import spock.lang.Specification

class RequestTypesTest extends Specification {

    def "HTTP 라인에서 파싱한 문자열로, 알맞은 메소드를 리턴하는지" () {
        given:
        String requestElement = "GET"

        when:
        RequestMethods type = RequestMethods.getByLineString(requestElement)

        then:
        type == RequestMethods.GET
    }
}
