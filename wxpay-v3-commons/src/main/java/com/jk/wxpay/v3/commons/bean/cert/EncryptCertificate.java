package com.jk.wxpay.v3.commons.bean.cert;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EncryptCertificate {

    private String algorithm;

    private String nonce;

    @JsonProperty("associated_data")
    private String associatedData;

    @JsonProperty("ciphertext")
    private String cipherText;

    public EncryptCertificate() {
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public EncryptCertificate setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public String getNonce() {
        return nonce;
    }

    public EncryptCertificate setNonce(String nonce) {
        this.nonce = nonce;
        return this;
    }

    public String getAssociatedData() {
        return associatedData;
    }

    public EncryptCertificate setAssociatedData(String associatedData) {
        this.associatedData = associatedData;
        return this;
    }

    public String getCipherText() {
        return cipherText;
    }

    public EncryptCertificate setCipherText(String cipherText) {
        this.cipherText = cipherText;
        return this;
    }
}
