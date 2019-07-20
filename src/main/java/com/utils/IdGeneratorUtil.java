package com.utils;

public class IdGeneratorUtil {

    private static Long productId = 1L;
    private static Long userId = 1L;
    private static Long orderId = 1L;
    private static Long basketId = 1L;
    private static Long codeId = 1L;

    public static Long getProductId() {
        return productId++;
    }

    public static Long getUserId() {
        return userId++;
    }

    public static Long getOrderId() {
        return orderId++;
    }

    public static Long getBasketId() {
        return basketId++;
    }

    public static Long getCodeId() {
        return codeId++;
    }
}
