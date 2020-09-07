package com.jk.wxpay.v3.commons.bean.cert;


import com.google.gson.annotations.SerializedName;

/**
 * 一个证书描述
 */
public class EncryptCertificateDescription {

    @SerializedName("serial_no")
    private String serialNo;

    @SerializedName("effective_time")
    private String effectiveTime;

    @SerializedName("expire_time")
    private String expireTime;

    @SerializedName("encrypt_certificate")
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
