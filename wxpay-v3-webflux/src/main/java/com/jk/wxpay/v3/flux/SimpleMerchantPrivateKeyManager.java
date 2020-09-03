package com.jk.wxpay.v3.flux;

import com.jk.wxpay.v3.commons.bean.MerchantPrivateKey;
import com.jk.wxpay.v3.commons.exception.WxErrorException;
import com.jk.wxpay.v3.commons.util.PemUtils;
import com.jk.wxpay.v3.reactor.MerchantPrivateKeyManager;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

/**
 * 简单的商户私钥服务，从resource 中初始化。
 */
public class SimpleMerchantPrivateKeyManager implements MerchantPrivateKeyManager {

    /**
     * 在resource的路径
     */
    private String privateKeyPath = "pem/wx_key.pem";
    private MerchantPrivateKey mchPrivateKey;

    /**
     * 商户证书的序列号
     */
    private String certificatesSerialNumber;

    public SimpleMerchantPrivateKeyManager() {
    }

    public SimpleMerchantPrivateKeyManager setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
        return this;
    }

    public SimpleMerchantPrivateKeyManager setCertificatesSerialNumber(String certificatesSerialNumber) {
        this.certificatesSerialNumber = certificatesSerialNumber;
        return this;
    }

    @Override
    public Mono<MerchantPrivateKey> getPrivateKey(String mchId) {
        try {
            if (this.mchPrivateKey == null) {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(privateKeyPath);
                PrivateKey privateKey = PemUtils.loadPrivateKey(inputStream);
                this.mchPrivateKey = new MerchantPrivateKey(this.certificatesSerialNumber, privateKey);
            }
            return Mono.just(this.mchPrivateKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IllegalArgumentException | IOException e) {
            return Mono.error(new WxErrorException(e.getClass().getSimpleName(), e.getMessage()));
        }
    }
}
