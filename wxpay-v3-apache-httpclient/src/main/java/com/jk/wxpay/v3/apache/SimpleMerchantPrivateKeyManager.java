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

    public void setMchPrivateKey(MerchantPrivateKey mchPrivateKey) {
        this.mchPrivateKey = mchPrivateKey;
    }

    @Override
    public MerchantPrivateKey getPrivateKey(String mchId) {
        try {
            if (this.mchPrivateKey == null) {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(privateKeyPath);
                PrivateKey privateKey = PemUtils.loadPrivateKey(inputStream);
                this.mchPrivateKey = new MerchantPrivateKey(this.certificatesSerialNumber, privateKey);
            }

            return this.mchPrivateKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IllegalArgumentException | IOException e) {
            throw new WxErrorException(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public String getApiV3Key(String mchId) {
        return null;
    }
}
