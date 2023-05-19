package com.jk.wxpay.v3.commons.bean.direct;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 单品列表信息
 * 条目个数限制：【1，undefined】
 */
public class GoodsDetail {



    /**
     * 商户侧商品编码, 由半角的大小写字母、数字、中划线、下划线中的一种或几种组成。
     * 示例值：商品编码
     * [r]
     */
    @JsonProperty("merchant_goods_id")
    private String merchantGoodsId;

    /**
     * 微信侧商品编码,
     * 微信支付定义的统一商品编号（没有可不传）
     * 示例值：1001
     * [o]
     */
    @JsonProperty("wechatpay_goods_id")
    private String wxpayGoodsId;

    /**
     * 商品的实际名称
     * [o]
     */
    @JsonProperty("goods_name")
    private String goodsName;

    /**
     * 用户购买的数量
     * 示例值：1
     * [r]
     */
    private Integer quantity;

    /**
     * 商品单价，单位为分
     * 示例值：828800
     * [r]
     */
    @JsonProperty("unit_price")
    private Integer unitPrice;

    public GoodsDetail() {
    }

    public String getMerchantGoodsId() {
        return merchantGoodsId;
    }

    public GoodsDetail setMerchantGoodsId(String merchantGoodsId) {
        this.merchantGoodsId = merchantGoodsId;
        return this;
    }

    public String getWxpayGoodsId() {
        return wxpayGoodsId;
    }

    public GoodsDetail setWxpayGoodsId(String wxpayGoodsId) {
        this.wxpayGoodsId = wxpayGoodsId;
        return this;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public GoodsDetail setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public GoodsDetail setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public GoodsDetail setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }
}
