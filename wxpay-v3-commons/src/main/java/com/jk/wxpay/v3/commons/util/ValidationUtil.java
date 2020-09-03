package com.jk.wxpay.v3.commons.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.util.Base64;

/**
 * 微信返回的数据的校验。
 */
public class ValidationUtil {

    /**
     * 参数的连接， 根据微信支付官方协议而来。
     * @param timestamp
     * @param nonce
     * @param body
     * @return
     */
    private static String concat(String timestamp, String nonce, String body) {
        return timestamp + "\n"
                + nonce + "\n"
                + body + "\n";
    }

    /**
     * 校验合法性，通过给予的证书。
     * @param certificate  公钥证书
     * @param timestamp 时间戳，来自微信返回请求。
     * @param nonce 随机字符串，来自微信返回。
     * @param body  返回的body 请求体。
     * @param signature
     * @return
     */
    public static boolean validate(X509Certificate certificate, String timestamp, String nonce,
                            String body, String signature)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        String cmsg = concat(timestamp, nonce, body);
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initVerify(certificate);
        sign.update(cmsg.getBytes());
        return sign.verify(Base64.getDecoder().decode(signature));
    }
}
