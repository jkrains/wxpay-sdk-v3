package com.jk.wxpay.v3.commons.bean;

import java.security.PrivateKey;

/**
 * 商户私钥信息。
 */
public class MerchantPrivateKey {

    private String serialNumber;
    private PrivateKey privateKey;

    public MerchantPrivateKey(String serialNumber, PrivateKey privateKey) {
        this.serialNumber = serialNumber;
        this.privateKey = privateKey;
    }

    public MerchantPrivateKey() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public MerchantPrivateKey setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public MerchantPrivateKey setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
        return this;
    }
}
