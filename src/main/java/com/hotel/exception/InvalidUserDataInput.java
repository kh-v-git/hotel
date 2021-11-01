package com.hotel.exception;

public class InvalidUserDataInput extends Exception{
    public InvalidUserDataInput(String errorMassage, Throwable err) {
        super(errorMassage, err);
    }
    public InvalidUserDataInput(String errorMessage) {
        super(errorMessage);
    }
}
