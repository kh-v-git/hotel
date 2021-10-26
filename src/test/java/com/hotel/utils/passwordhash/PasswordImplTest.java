package com.hotel.utils.passwordhash;

import org.junit.Assert;
import org.junit.Test;


public class PasswordImplTest {
    //Generated outside from app
    public static final String TEST_HASH = "f66555b0e9a5ef1402673b6df12a1f68d19300c84630b50eb5c086e76b2c06dd4350e0359faef6118b5ffab3053939e227113c26289f5e7ea1aad063e3c8a7a8";

    @Test
    public void shouldHashPasswordReadingSaltFromProperty() throws Exception {
        //
        // Given
        //
        String pass = "qwerty123qwerty";
        //
        // When
        //
        String result = PasswordImpl.hashPassword(pass);
        //
        // Then
        //
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(TEST_HASH, result);
    }
}