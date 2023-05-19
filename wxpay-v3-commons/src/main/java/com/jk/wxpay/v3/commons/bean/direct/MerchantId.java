package com.jk.wxpay.v3.commons.bean.direct;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 商户号的json格式。
 */
public class MerchantId {

    @JsonProperty("mchid")
    private String mchId;

    public MerchantId(String mchId) {
        this.mchId = mchId;
    }

    public MerchantId() {
    }

    public String getMchId() {
        return mchId;
    }

    public MerchantId setMchId(String mchId) {
        this.mchId = mchId;
        return this;
    }
}
