package com.jk.wxpay.v3.commons.bean.cert;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 一个证书描述
 */
public class EncryptCertificateDescription {

    @JsonProperty("serial_no")
    private String serialNo;

    @JsonProperty("effective_time")
    private String effectiveTime;

    @JsonProperty("expire_time")
    private String expireTime;

    @JsonProperty("encrypt_certificate")
    private EncryptCertificate encryptCertificate;

    public EncryptCertificateDescription() {
    }

    public String getSerialNo() {
        return serialNo;
    }

    public EncryptCertificateDescription setSerialNo(String serialNo) {
        this.serialNo = serialNo;
        return this;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public EncryptCertificateDescription setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
        return this;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public EncryptCertificateDescription setExpireTime(String expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    public EncryptCertificate getEncryptCertificate() {
        return encryptCertificate;
    }

    public EncryptCertificateDescription setEncryptCertificate(EncryptCertificate encryptCertificate) {
        this.encryptCertificate = encryptCertificate;
        return this;
    }
}
