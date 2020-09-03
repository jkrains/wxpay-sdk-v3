package com.jk.wxpay.v3.commons.bean.direct.query;

import com.google.gson.annotations.SerializedName;

/**
 * 查询订单的金额结果。
 */
public class QueryOrderAmount {
    /**
     * 订单总金额，单位为分。
     * 示例值：100
     */
    private Integer total;

    /**
     * 用户支付金额，单位为分。
     * 示例值：100
     */
    @SerializedName("payer_total")
    private Integer payerTotal;

    /**
     * CNY：人民币，境内商户号仅支持人民币。
     * 示例值：CNY
     */
    private String currency;

    /**
     * 用户支付币种
     * 示例值：CNY
     */
    @SerializedName("payer_currency")
    private String payerCurrency;

    public QueryOrderAmount() {
    }

    public Integer getTotal() {
        return total;
    }

    public QueryOrderAmount setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getPayerTotal() {
        return payerTotal;
    }

    public QueryOrderAmount setPayerTotal(Integer payerTotal) {
        this.payerTotal = payerTotal;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public QueryOrderAmount setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getPayerCurrency() {
        return payerCurrency;
    }

    public QueryOrderAmount setPayerCurrency(String payerCurrency) {
        this.payerCurrency = payerCurrency;
        return this;
    }
}
