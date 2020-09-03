package com.jk.wxpay.v3.commons.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;

/**
 * 微信 请求接口认证工具类。 构建认证信息。
 * 认证类型 + 认证信息构建了一个认证头。
 * 微信的认证类型目前是固定的 "WECHATPAY2-SHA256-RSA2048"
 */
public class AuthorizationUtils {

    /**
     * 连接认证信息。保证传递的这些信息均有效。 函数内部不会进行参数合法性校验。
     * @param nonce 随机字符串
     * @param timestamp 单位位秒的时间戳
     * @param method http请求方法
     * @param path 域名之后的path. 如果携带参数则需要一块填进去。
     * @param body  请求的body, 对于get 请求需要传递"" 空字符串
     * @return 返回组合后的字符串
     */
    private static String concatAuthorizationInfo(String nonce, long timestamp, String method, String path, String body) {
        return method + "\n"
                + path + "\n"
                + timestamp + "\n"
                + nonce + "\n"
                + body + "\n";
    }

    /**
     * 构建一个请求认证信息。
     * @param merchantId 商户号。
     * @param privateKey 商户的私钥。
     * @param keyNumber 商户的私钥编号。
     * @param method http请求方法
     * @param path 域名之后的path. 如果携带参数则需要一块填进去。
     * @param body  请求的body, 对于get 请求需要传递"" 空字符串
     * @return
     */
    public static String buildAuthorizationInfo(String merchantId, PrivateKey privateKey, String keyNumber, String method, String path, String body)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
       String nonceStr = StringUtils.generateNonceStr();
       long timestamp = System.currentTimeMillis() / 1000;

       String msg = concatAuthorizationInfo(nonceStr, timestamp, method, path, body);
       String sign = SHA256SignUtils.signAndEncodeWithBase64(privateKey, msg);

        String result = "mchid=\"" + merchantId + "\","
                + "nonce_str=\"" + nonceStr + "\","
                + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + keyNumber + "\","
                + "signature=\"" + sign + "\"";
        return result;
    }
}
