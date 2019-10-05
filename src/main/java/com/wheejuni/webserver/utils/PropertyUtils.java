package com.wheejuni.webserver.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.util.Resources;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class PropertyUtils {
    private static final String COMMON_PROPERTIES_DIR_PREFIX = "classpath:";
    private static final Logger LOG = LoggerFactory.getLogger(PropertyUtils.class);

    public static Properties resolveProperties(String fileName) {
        String fileDir = COMMON_PROPERTIES_DIR_PREFIX + fileName;
        Properties resolvedProperties = new Properties();

        try {
            FileReader resources = new FileReader(fileDir);

            resolvedProperties.load(resources);
        } catch (IOException e) {
            LOG.error("error while resolving properties! {}", e);
        }

        return resolvedProperties;
    }
}
