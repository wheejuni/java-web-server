package com.wheejuni.webserver.view;

import com.wheejuni.webserver.http.request.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class RequestProcessor {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ControllerResolver resolver;

    public RequestProcessor(ControllerResolver resolver) {
        this.resolver = resolver;
    }

    public String processRequest(RequestMapping mapping) {
        String returnMessage = null;
        try {
            returnMessage = (String)resolver.resolveMatchingControllerMethod(mapping).invoke(new Object());
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("error while processing request! {}", e);
        }

        return returnMessage;
    }
}
