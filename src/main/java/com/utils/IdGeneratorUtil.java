package com.utils;

public class IdGeneratorUtil {

    private static Long productId = 1L;
    private static Long userId = 1L;
    private static Long orderId = 1L;

    public static Long getProductId() {
        return productId++;
    }

    public static Long getUserId() {
        return userId++;
    }

    public static Long getOrderId() {
        return orderId++;
    }
}
