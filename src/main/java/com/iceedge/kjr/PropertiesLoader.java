package com.iceedge.kjr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

    public void loadProperties(Properties properties,
                               Class clazz,
                               String resource,
                               String... alternateLocations) {
        InputStream stream = clazz.getResourceAsStream(resource);
        logger.debug("Will attempt to load properties from " + resource);
        loadProperties(properties, stream, alternateLocations);
    }

    public void loadProperties(Properties properties,
                               InputStream stream,
                               String... alternateLocations) {
        if (properties == null) {
            properties = new Properties();
        }

        if (stream != null) {
            try {
                properties.load(stream);
            } catch (IOException e) {
                logger.trace("Unable to load properties from " + stream.toString(), e);
            }
        }
        for (String alternateLocation : alternateLocations) {
            try (FileInputStream alternateStream = new FileInputStream(alternateLocation)) {
                logger.debug("Loading properties from alternate " + alternateLocation);
                properties.load(alternateStream);
            } catch (IOException e) {
                logger.trace("Unable to load properties from " + alternateLocation, e);
            }
        }
    }
}
