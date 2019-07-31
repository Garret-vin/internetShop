package com.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

public class HashUtilTest {

    @Test
    public void getSaltedPassword() {
        byte[] array = {0, 1, 2, 3, 4, 5};
        String start = DigestUtils.sha256Hex("012");
        String end = DigestUtils.sha256Hex("345");
        String testPassword = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        String expectedResult = DigestUtils.sha256Hex(start + testPassword + end);
        String result = HashUtil.getSaltedPassword("1", array);
        Assert.assertEquals(expectedResult, result);
    }
}