package com.iceedge.kjr;

import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class PropertiesLoaderTest {
    @Test
    public void loadPropertiesTest() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties properties = new Properties();


        propertiesLoader.loadProperties(properties, this.getClass(), "/app.properties");

        assertEquals("true", properties.getProperty("default"));
        assertEquals("false", properties.getProperty("override"));
        assertEquals("false", properties.getProperty("developer"));
    }

    @Test
    public void overrideProperties() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties properties = new Properties();


        propertiesLoader.loadProperties(properties, this.getClass(), "/app.properties", "src/test/unmanaged-resources/app.properties", "src/test/unmanaged-resources/developer/app.properties");

        assertEquals("true", properties.getProperty("default"));
        assertEquals("true", properties.getProperty("override"));
        assertEquals("true", properties.getProperty("developer"));
    }
}
