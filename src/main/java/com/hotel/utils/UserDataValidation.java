package com.hotel.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Check data from user using patterns
 */
public class UserDataValidation {
    private static final Logger log = LogManager.getLogger(UserDataValidation.class);

    private static final String PHONE_PATTERN = "^[0-9]{12}$";
    private static final String NAME_PATTERN = "^(?=.{1,50}$)\\p{L}+(?:[-'\\s]\\p{L}+)*$";
    private static final String STRING_PATTERN = "^(?=.{1,40}$)\\p{L}+$";
    private static final String CARD_PATTERN ="^([0-9]{16})$";
    private static final String INT_PATTERN = "^[0-9]{2}$";
    private static final String DEC_PATTERN = "^[0-9]{4}\\.?[0-9]{2}$";
    //RFC5322
    private static final String EMAIL_PATTERN =
            "^(?:[a-z0-9!#$%&'*+\\=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$";

    /**
     * Check String data for matching pattern
     * @param data Input string to be checked
     * @param param Type of data
     * @return Check result. TRUE when data matches param`s pattern.
     */
    public static boolean checkInputData(String data, String param) {
        boolean checkStatus = false;
        switch (param) {
            case "phone":
                checkStatus = patternCheck(data, PHONE_PATTERN);
                break;
            case "name":
                checkStatus = patternCheck(data, NAME_PATTERN);
                break;
            case "string":
                checkStatus = patternCheck(data, STRING_PATTERN);
                break;
            case "card":
                checkStatus = patternCheck(data, CARD_PATTERN);
                break;
            case "digitalINT":
                checkStatus = patternCheck(data, INT_PATTERN);
                break;
            case "digitalDEC":
                checkStatus = patternCheck(data, DEC_PATTERN);
                break;
            case "email":
                checkStatus = patternCheck(data, EMAIL_PATTERN);
                break;
            default:
                log.log(Level.DEBUG, String.format("Data validation failed. No '%s' found", param));
                break;
        }
        return checkStatus;
    }
    private static boolean patternCheck (String inpData, String pattern) {
        Pattern pat = Pattern.compile(pattern);
        Matcher mat = pat.matcher(inpData);
        return mat.find();
    }
}
