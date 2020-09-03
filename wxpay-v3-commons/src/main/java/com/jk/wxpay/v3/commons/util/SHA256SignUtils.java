package com.jk.wxpay.v3.commons.util;


import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

/**
 * SHAwithRSA 进行签名的工具类。 微信支付v3会用这个类，对请求的内容进行签名。
 */
public class SHA256SignUtils {

    /**
     * 使用给定的密钥，对指定的buffer进行签名
     * @param privateKey 给定的密钥。
     * @param message 需要签名的消息
     * @return 返回签名后的buffer
     */
    public static byte[] sign(PrivateKey privateKey, byte[] message)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privateKey);
        sign.update(message);
        return sign.sign();
    }

    /**
     * 将一个String字符串进行签名。
     * @param privateKey
     * @param message
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static String signString(PrivateKey privateKey, String message)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        return new String(sign(privateKey, message.getBytes()), StandardCharsets.UTF_8);
    }

    /**
     * 对给定的字符串进行签名，并将签名后的内容转换成base64编码。
     * @param privateKey
     * @param message
     * @return
     */
    public static String signAndEncodeWithBase64(PrivateKey privateKey, String message)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        return Base64.getEncoder().encodeToString(sign(privateKey, message.getBytes()));
    }
}
