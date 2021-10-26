package com.hotel.utils;

import com.hotel.exception.ReadPropertyException;
import org.junit.Assert;
import org.junit.Test;

public class ReadPropertiesTest {
    private static final String TEST_STRING = "test";
    private static final String PROPERTY_FILE = "application.properties";
    public static final String PROP_KEY = "password.salt";

    @Test
    public void readProps() throws Exception {
        //
        // Given
        //

        //
        // When
        //
        String result = ReadProperties.readProps(PROPERTY_FILE, PROP_KEY);
        //
        // Then
        //
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(TEST_STRING, result);
    }

    @Test(expected = ReadPropertyException.class)
    public void shouldThrowExceptionIfFileNotFound() throws Exception {
        //
        // Given
        //
        String propFile = "invalid";
        //
        // When
        //
        String result = ReadProperties.readProps(propFile, PROP_KEY);
    }

    @Test(expected = ReadPropertyException.class)
    public void shouldThrowExceptionIfPropertyNotFound() throws Exception {
        //
        // Given
        //
        String propKey = "password";
        //
        // When
        //
        String result = ReadProperties.readProps(PROPERTY_FILE, propKey);
    }
}