package com.jk.wxpay.v3.commons.bean.direct;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 优惠功能
 */
public class DiscountDetail {
    /**
     * 1、商户侧一张小票订单可能被分多次支付，订单原价用于记录整张小票的交易金额。
     * 2、当订单原价与支付金额不相等，则不享受优惠。
     * 3、该字段主要用于防止同一张小票分多次支付，以享受多次优惠的情况，正常支付订单不必上传此参数。
     * 示例值：608800
     * [o]
     */
    @JsonProperty("cost_price")
    private Integer costPrice;

    /**
     * 商家小票ID
     * 示例值：微信123
     * [o]
     */
    @JsonProperty("invoice_id")
    private String InvoiceId;

    /**
     * 单品列表信息
     * 条目个数限制：【1，undefined】
     * [o]
     */
    @JsonProperty("goods_detail")
    private GoodsDetail goodsDetail;

    public DiscountDetail(Integer costPrice, String invoiceId) {
        this.costPrice = costPrice;
        InvoiceId = invoiceId;
    }

    public DiscountDetail() {
    }

    public Integer getCostPrice() {
        return costPrice;
    }

    public DiscountDetail setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
        return this;
    }

    public String getInvoiceId() {
        return InvoiceId;
    }

    public DiscountDetail setInvoiceId(String invoiceId) {
        InvoiceId = invoiceId;
        return this;
    }

    public GoodsDetail getGoodsDetail() {
        return goodsDetail;
    }

    public DiscountDetail setGoodsDetail(GoodsDetail goodsDetail) {
        this.goodsDetail = goodsDetail;
        return this;
    }
}
