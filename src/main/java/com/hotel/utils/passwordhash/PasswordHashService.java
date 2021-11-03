package com.hotel.utils.passwordhash;

import com.hotel.exception.HashPasswordException;
import com.hotel.exception.ReadPropertyException;
import com.hotel.utils.ReadProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Hash user password using custom salt from application.properties file
 * with PBKDF2WithHmacSHA512
 */
public class PasswordHashService {
    private static final Logger log = LogManager.getLogger(PasswordHashService.class);

    private static String propsFile = "application.properties";
    private static String passSaltProps = "password.salt";
    private static String salt;
    private static String resultHash;

    /**
     * Hash input String
     * @param pass input String
     * @return hash input String
     * @throws HashPasswordException
     */
    public static String hashPassword(String pass) throws HashPasswordException {
        try {
            salt = ReadProperties.readProps(propsFile, passSaltProps);
        } catch (ReadPropertyException e) {
            throw new HashPasswordException("Property Salt no found", e);
        }
        try {
            resultHash = HashString.createHash(pass, salt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new HashPasswordException("Hash generation error", ex);
        }
        return resultHash;
    }
}
