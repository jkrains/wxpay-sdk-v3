package com.jk.wxpay.v3.commons.util;

import java.security.SecureRandom;

/**
 * 字符串的一些处理
 */
public class StringUtils {
    private static final String SYMBOLS =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * 获取一个随机的字符串, 如果count 无效，则count 为 32.
     * @return
     */
    public static String generateRandomString(int count) {
        if (count <= 0) {
            count = 32;
        }
        char[] nonceChars = new char[count];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }

    /**
     * 产生一个随机的32位的字符串。
     * @return
     */
    public static String generateNonceStr() {
        return generateRandomString(32);
    }
}
