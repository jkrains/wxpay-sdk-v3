package com.jk.wxpay.v3.commons.bean.direct.notify;

import com.google.gson.annotations.SerializedName;

/**
 * 通知资源数据
 * json格式，见示例
 */
public class PrepayNotifyResource {

    /**
     * 对开启结果数据进行加密的加密算法，目前只支持AEAD_AES_256_GCM
     * 示例值：AEAD_AES_256_GCM
     */
    private String algorithm;

    /**
     * Base64编码后的开启/停用结果数据密文
     * 示例值：sadsadsadsad
     */
    @SerializedName("ciphertext")
    private String cipherText;

    /**
     * 附加数据
     * 示例值：fdasfwqewlkja484w
     */
    @SerializedName("associated_data")
    private String associatedData;

    /**
     * 加密使用的随机串
     * 示例值：fdasflkja484w
     */
    private String nonce;

    /**
     * 回调摘要
     * 示例值：支付成功
     */
    private String summary;

    public PrepayNotifyResource() {
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public PrepayNotifyResource setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public String getCipherText() {
        return cipherText;
    }

    public PrepayNotifyResource setCipherText(String cipherText) {
        this.cipherText = cipherText;
        return this;
    }

    public String getAssociatedData() {
        return associatedData;
    }

    public PrepayNotifyResource setAssociatedData(String associatedData) {
        this.associatedData = associatedData;
        return this;
    }

    public String getNonce() {
        return nonce;
    }

    public PrepayNotifyResource setNonce(String nonce) {
        this.nonce = nonce;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public PrepayNotifyResource setSummary(String summary) {
        this.summary = summary;
        return this;
    }
}
