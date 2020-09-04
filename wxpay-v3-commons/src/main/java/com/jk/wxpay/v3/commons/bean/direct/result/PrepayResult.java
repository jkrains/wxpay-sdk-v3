package com.jk.wxpay.v3.commons.bean.direct.result;

import com.google.gson.annotations.SerializedName;

/**
 * 正常 预下单 返回结果。
 */
public class PrepayResult {

    /**
     * 预支付交易会话标识。
     * 示例值：wx201410272009395522657a690389285100
     */
    @SerializedName("prepay_id")
    private String prepayId;

    public PrepayResult() {
    }

    public PrepayResult(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public PrepayResult setPrepayId(String prepayId) {
        this.prepayId = prepayId;
        return this;
    }

    @Override
    public String toString() {
        return "PrepayResult{" +
                "prepayId='" + prepayId + '\'' +
                '}';
    }
}
