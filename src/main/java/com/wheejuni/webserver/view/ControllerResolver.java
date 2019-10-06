package com.wheejuni.webserver.view;

import com.wheejuni.webserver.http.exceptions.NoPathMappingFoundException;
import com.wheejuni.webserver.http.request.RequestLine;
import com.wheejuni.webserver.http.request.RequestMapping;
import com.wheejuni.webserver.http.request.RequestMethods;
import com.wheejuni.webserver.utils.PropertyUtils;
import com.wheejuni.webserver.view.prototype.Controller;
import com.wheejuni.webserver.view.prototype.HttpRequest;
import com.wheejuni.webserver.view.prototype.RestController;
import org.codehaus.groovy.util.StringUtil;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;

public class ControllerResolver {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static ControllerResolver instance;

    private List<Method> requestProcessingMethodsFromControllers = new ArrayList<>();
    private List<Method> requestProcessingMethodsFromRestControllers = new ArrayList<>();
    private Properties controllerProperties;

    public static ControllerResolver getInstance() {
        if (instance == null) {
            instance = new ControllerResolver();
            instance.initiate();
        }
        return instance;
    }

    private ControllerResolver() {
        this.controllerProperties = PropertyUtils.resolveProperties("controller.properties");
    }

    private void initiate() {
        String basePackageProperty = controllerProperties.getProperty("controller.basePackage");
        Reflections reflections = basePackageProperty != null ? new Reflections(basePackageProperty) : new Reflections();

        Set<Class<?>> controllerClasses = reflections.getTypesAnnotatedWith(Controller.class);
        Set<Class<?>> restControllerClasses = reflections.getTypesAnnotatedWith(RestController.class);

        for(Class<?> controller: controllerClasses) {
            for(Method m: controller.getMethods()) {
                if(m.isAnnotationPresent(HttpRequest.class)) {
                    requestProcessingMethodsFromControllers.add(m);
                }
            }
        }

        for(Class<?> controller: restControllerClasses) {
            for(Method m: controller.getMethods()) {
                if(m.isAnnotationPresent(HttpRequest.class)) {
                    requestProcessingMethodsFromRestControllers.add(m);
                }
            }
        }

        log.info("Request controller initialization complete, scanned {} controllers with {} request processing methods",
                controllerClasses.size() + restControllerClasses.size(),
                requestProcessingMethodsFromControllers.size() + requestProcessingMethodsFromRestControllers.size());
    }

    public Method resolveMatchingControllerMethod(RequestMapping mapping) {
        return this.requestProcessingMethodsFromControllers.stream()
                .filter(m -> {
                    HttpRequest requestAnnotation = m.getAnnotation(HttpRequest.class);
                    List<RequestMethods> listifiedMethods = Arrays.asList(requestAnnotation.methods());
                    String path = requestAnnotation.path();

                    return mapping.isMatchingRequest(path, listifiedMethods);
                }).findFirst().orElseThrow(NoPathMappingFoundException::new);
    }
}
