package com.hotel.utils.passwordhash;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

/**
 * PBKDF2 salted String (password) hashing.
 */
public class HashString {
    private static final Logger log = LogManager.getLogger(HashString.class);

    private static final String KEY_FACTORY_ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final int HASH_BYTES = 64;
    private static final int PBKDF2_ITERATIONS = 100000;

    /**
     *
     * @param password String password to be converted
     * @param salt String salt added to pass hash
     * @return String hash of password with hash
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static String createHash(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        return createHash(password.toCharArray(), salt.toCharArray());
    }

    private static String createHash(char[] password, char[] saltArray)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] saltBytes = HashString.toBytesArray(saltArray);
        byte[] hash = pbkdf2(password, saltBytes, PBKDF2_ITERATIONS, HASH_BYTES);
        return toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(KEY_FACTORY_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

    private static byte[] toBytesArray(char[] chars) {
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(byteBuffer.array(), (byte) 0);
        return bytes;
    }


}
