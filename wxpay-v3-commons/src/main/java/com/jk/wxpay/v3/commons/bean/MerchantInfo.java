package com.jk.wxpay.v3.commons.bean;

public class MerchantInfo {
    private String merchantId;
    private MerchantPrivateKey privateKey;

    public MerchantInfo(String merchantId, MerchantPrivateKey privateKey) {
        this.merchantId = merchantId;
        this.privateKey = privateKey;
    }

    public MerchantInfo() {
    }

    public String getMerchantId() {
        return merchantId;
    }

    public MerchantInfo setMerchantId(String merchantId) {
        this.merchantId = merchantId;
        return this;
    }

    public MerchantPrivateKey getPrivateKey() {
        return privateKey;
    }

    public MerchantInfo setPrivateKey(MerchantPrivateKey privateKey) {
        this.privateKey = privateKey;
        return this;
    }
}
