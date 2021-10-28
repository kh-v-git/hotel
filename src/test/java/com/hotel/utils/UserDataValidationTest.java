package com.hotel.utils;

import org.junit.Assert;
import org.junit.Test;

public class UserDataValidationTest {

    @Test
    public void shouldValidatePhonePattern() {
        //
        // Given
        //
        String phoneNumber = "380959954565";
        String param = "phone";
        //
        // When
        //
        boolean result = StringPatternValidation.checkInputData(phoneNumber, param);
        //
        // Then
        //
        Assert.assertTrue(result);
    }

    @Test
    public void shouldValidateNamePattern() {
        //
        // Given
        //
        String phoneNumber = "Vadyl-GykТук";
        String param = "name";
        //
        // When
        //
        boolean result = StringPatternValidation.checkInputData(phoneNumber, param);
        //
        // Then
        //
        Assert.assertTrue(result);
    }

    @Test
    public void shouldValidateStringPattern() {
        //
        // Given
        //
        String phoneNumber = "JDDLLJсмсм";
        String param = "string";
        //
        // When
        //
        boolean result = StringPatternValidation.checkInputData(phoneNumber, param);
        //
        // Then
        //
        Assert.assertTrue(result);
    }

    @Test
    public void shouldValidateCardPattern() {
        //
        // Given
        //
        String phoneNumber = "4149567887789820";
        String param = "card";
        //
        // When
        //
        boolean result = StringPatternValidation.checkInputData(phoneNumber, param);
        //
        // Then
        //
        Assert.assertTrue(result);
    }

    @Test
    public void shouldValidateIntPattern() {
        //
        // Given
        //
        String phoneNumber = "41";
        String param = "digitalINT";
        //
        // When
        //
        boolean result = StringPatternValidation.checkInputData(phoneNumber, param);
        //
        // Then
        //
        Assert.assertTrue(result);
    }

    @Test
    public void shouldValidateDecimalPattern() {
        //
        // Given
        //
        String phoneNumber = "1230.45";
        String param = "digitalDEC";
        //
        // When
        //
        boolean result = StringPatternValidation.checkInputData(phoneNumber, param);
        //
        // Then
        //
        Assert.assertTrue(result);
    }

    @Test
    public void shouldValidateEmailPattern() {
        //
        // Given
        //
        String phoneNumber = "dfsldjf&sdfsdl.sdfs@gma.com";
        String param = "email";
        //
        // When
        //
        boolean result = StringPatternValidation.checkInputData(phoneNumber, param);
        //
        // Then
        //
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseNoParamPattern() {
        //
        // Given
        //
        String phoneNumber = "dfsldjf&sdfsdl.sdfs@gma.com";
        String param = "emails";
        //
        // When
        //
        boolean result = StringPatternValidation.checkInputData(phoneNumber, param);
        //
        // Then
        //
        Assert.assertFalse(result);
    }
}