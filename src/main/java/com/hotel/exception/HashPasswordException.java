package com.hotel.exception;

public class HashPasswordException extends Exception {
    public HashPasswordException(String errorMassage, Throwable err) {
        super(errorMassage, err);
    }
}
