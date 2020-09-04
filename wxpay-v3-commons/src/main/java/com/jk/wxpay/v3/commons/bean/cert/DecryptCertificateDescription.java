package com.jk.wxpay.v3.commons.bean.cert;

/**
 * 解密后的证书信息。
 */
public class DecryptCertificateDescription {
    /**
     * 序列号
     */
    private String serialNo;

    private String effectiveTime;
    private String expireTime;

    /**
     * 证书内容
     */
    private String certText;

    public DecryptCertificateDescription() {
    }

    public DecryptCertificateDescription(String serialNo, String effectiveTime, String expireTime, String certText) {
        this.serialNo = serialNo;
        this.effectiveTime = effectiveTime;
        this.expireTime = expireTime;
        this.certText = certText;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public DecryptCertificateDescription setSerialNo(String serialNo) {
        this.serialNo = serialNo;
        return this;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public DecryptCertificateDescription setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
        return this;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public DecryptCertificateDescription setExpireTime(String expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    public String getCertText() {
        return certText;
    }

    public DecryptCertificateDescription setCertText(String certText) {
        this.certText = certText;
        return this;
    }
}
