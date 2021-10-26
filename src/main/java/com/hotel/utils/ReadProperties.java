package com.hotel.utils;

import com.hotel.exception.ReadPropertyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Read property keys from custom file
 */
public class ReadProperties {
    private static final Logger log = LogManager.getLogger(ReadProperties.class);


    private ReadProperties() {
    }
    /**
     * Read props key
     * @param propertyFile property file container
     * @param propertyToRead property to read
     * @return property
     * @throws ReadPropertyException
     */
    public static String readProps(String propertyFile, String propertyToRead) throws ReadPropertyException {
        try (InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(propertyFile)) {
            if (inputStream == null) {
                throw new ReadPropertyException("File no found " + propertyFile);
            }
            Properties prop = new Properties();
            prop.load(inputStream);
            String property =  prop.getProperty(propertyToRead);
            if (property == null) {
                throw new ReadPropertyException("Property no found " + propertyToRead);
            }
            return property;
        } catch (IOException e) {
            throw new ReadPropertyException("Property no found " + propertyToRead, e);
        }
    }
}
