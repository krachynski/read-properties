package com.iceedge.kjr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Properties props = new Properties();

    public static void main(String[] args) {
        Main main = new Main();

        try {
            main.loadProps();
        } catch (IOException ex) {
            logger.error("Could not find my configuration");
        }

        logger.info("Greeting: {}", props.getProperty("greeting", "MISSING GREETING"));
        logger.info("Quote: {}", props.getProperty("quote", "MISSING QUOTE"));
    }

    private void loadProps() throws IOException {
        props.load(this.getClass().getResourceAsStream("/app.properties"));


        try (FileInputStream e = new FileInputStream("app.properties")) {
            props.load(e);
        } catch (FileNotFoundException ex) {
            logger.debug("No overriding property file found");
        }

        try (FileInputStream e = new FileInputStream("developer/app.properties")) {
            props.load(e);
        } catch (FileNotFoundException ex) {
            logger.debug("No overriding developer property file found");
        }

    }
}
