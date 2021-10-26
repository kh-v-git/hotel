package com.hotel.exception;

/**
 * Throws exception when property read failed
 */
public class ReadPropertyException extends Exception{
    public ReadPropertyException(String errorMassage, Throwable err) {
        super(errorMassage, err);
    }

    public ReadPropertyException(String errorMessage) {
        super(errorMessage);
    }
}
