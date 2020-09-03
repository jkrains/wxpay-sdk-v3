package com.jk.wxpay.v3.commons.bean.direct;

/**
 * 描述订单金额
 */
public class OrderAmount {
    /**
     * 订单总金额，单位为分。
     * 示例值：100
     * [r]
     */
    private int total;

    /**
     * CNY：人民币，境内商户号仅支持人民币。
     * 示例值：CNY
     * [o]
     */
    private String currency;

    public OrderAmount() {
    }

    public OrderAmount(int total, String currency) {
        this.total = total;
        this.currency = currency;
    }

    public int getTotal() {
        return total;
    }

    public OrderAmount setTotal(int total) {
        this.total = total;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public OrderAmount setCurrency(String currency) {
        this.currency = currency;
        return this;
    }
}
