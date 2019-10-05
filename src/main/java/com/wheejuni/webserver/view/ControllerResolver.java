package com.wheejuni.webserver.view;

import com.wheejuni.webserver.utils.PropertyUtils;
import com.wheejuni.webserver.view.prototype.Controller;
import com.wheejuni.webserver.view.prototype.HttpRequest;
import org.codehaus.groovy.util.StringUtil;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class ControllerResolver {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static ControllerResolver instance;

    private List<Method> requestHandlerMethods;
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
        List<Method> requestProcessingMethodsFromControllers = new ArrayList<>();

        for(Class<?> controller: controllerClasses) {
            for(Method m: controller.getMethods()) {
                if(m.isAnnotationPresent(HttpRequest.class)) {
                    requestProcessingMethodsFromControllers.add(m);
                }
            }
        }
        log.info("Request controller initialization complete, scanned {} controllers with {} request processing methods",
                controllerClasses.size(), requestProcessingMethodsFromControllers.size());
    }
}
