package com.hotel.utils.passwordhash;

import org.junit.Assert;
import org.junit.Test;

public class HashStringTest {
    //Generated outside from app
    public static final String testHash = "23b6323b0f29f0695557491a9a4d21c984abc909a805a02a8b73488b8ac3f9b1f14d6b28f9bd03b46d52ddb902eac074916f8343220fe8012fbd6d0d82301294";
    public static final String salt = "l3nms&3sS9NDS";

    @Test
    public void shouldGeneratePasswordHash() throws Exception {
        //
        // Given
        //
        String pass = "qwerty123qwerty";
        //
        // When
        //
        String result = HashString.createHash(pass, salt);
        //
        // Then
        //
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(testHash, result);
    }


}