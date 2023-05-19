package com.jk.wxpay.v3.apache;

import com.jk.wxpay.v3.block.MerchantPrivateKeyManager;
import com.jk.wxpay.v3.commons.bean.MerchantPrivateKey;
import com.jk.wxpay.v3.commons.exception.WxErrorException;
import com.jk.wxpay.v3.commons.util.PemUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

/**
 * 简单的商户私钥服务，从resource 中初始化。可以根据不同的环境实现不同的管理器。
 *
 */
public class SimpleMerchantPrivateKeyManager implements MerchantPrivateKeyManager {

    /**
     * 在resource的路径
     */
    private String privateKeyPath = "pem/wx_key.pem";
    private MerchantPrivateKey mchPrivateKey;
    private String apiV3Key;
    /**
     * 商户证书的序列号
     */
    private String certSerialNumber;

    public SimpleMerchantPrivateKeyManager() {
    }

    public SimpleMerchantPrivateKeyManager setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
        return this;
    }

    public SimpleMerchantPrivateKeyManager setCertSerialNumber(String certSerialNumber) {
        this.certSerialNumber = certSerialNumber;
        return this;
    }

    public SimpleMerchantPrivateKeyManager setApiV3Key(String apiV3Key) {
        this.apiV3Key = apiV3Key;
        return this;
    }

    @Override
    public MerchantPrivateKey getPrivateKey(String mchId) {
        try {
            if (this.mchPrivateKey == null) {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(privateKeyPath);
                PrivateKey privateKey = PemUtils.loadPrivateKey(inputStream);
                this.mchPrivateKey = new MerchantPrivateKey(this.certSerialNumber, privateKey);
            }

            return this.mchPrivateKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IllegalArgumentException | IOException e) {
            throw new WxErrorException(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public String getApiV3Key(String mchId) {
        return this.apiV3Key;
    }
}
