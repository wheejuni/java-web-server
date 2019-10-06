package com.wheejuni.webserver.view.prototype;

import com.wheejuni.webserver.http.request.RequestMethods;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface HttpRequest {
    String path() default "/";
    RequestMethods[] methods();
}
