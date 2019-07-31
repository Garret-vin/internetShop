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
        String testPassword = DigestUtils.sha256Hex("1");
        String expectedResult = DigestUtils.sha256Hex(start + testPassword + end);
        String result = HashUtil.getSaltedPassword("1", array);
        Assert.assertEquals(expectedResult, result);
        Assert.assertNotNull("Result is null", result);
    }
}