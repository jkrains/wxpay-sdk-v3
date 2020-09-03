package com.jk.wxpay.v3.commons.bean.direct;

import com.google.gson.annotations.SerializedName;
import com.jk.wxpay.v3.commons.bean.direct.query.QueryGoodsDetail;

/**
 * 促销优惠功能，享受优惠时返回该字段，该字段在查询订单状态时返回。
 */
public class PromotionDetail {
    /**
     *券ID
     * 示例值：109519
     * [r]
     */
    @SerializedName("coupon_id")
    private String couponId;

    /**
     *优惠名称
     * 示例值：单品惠-6
     * [o]
     */
    private String name;

    /**
     *GLOBAL：全场代金券
     * SINGLE：单品优惠
     * 示例值：GLOBAL
     * [o]
     */
    private String scope;

    /**
     *  CASH：充值
     * NOCASH：预充值
     * 示例值：CASH
     * [o]
     */
    private String type;

    /**
     *优惠券面额
     * 示例值：100
     * [r]
     */
    private Integer amount;

    /**
     *活动ID
     * 示例值：931386
     * [o]
     */
    @SerializedName("stock_id")
    private String stockId;

    /**
     *微信出资，单位为分
     * 示例值：0
     * [o]
     */
    @SerializedName("wechatpay_contribute")
    private Integer wechatPayContribute;

    /**
     *商户出资，单位为分
     * 示例值：0
     * [o]
     */
    @SerializedName("merchant_contribute")
    private Integer merchantContribute;

    /**
     *其他出资，单位为分
     * 示例值：0
     * [o]
     */
    @SerializedName("other_contribute")
    private Integer otherContribute;

    /**
     *CNY：人民币，境内商户号仅支持人民币。
     * 示例值：CNY
     * [o]
     */
    private String currency;

    /**
     *单品列表信息
     * [o]
     */
    @SerializedName("goods_detail")
    private QueryGoodsDetail goodsDetail;

    public PromotionDetail() {
    }

    public String getCouponId() {
        return couponId;
    }

    public PromotionDetail setCouponId(String couponId) {
        this.couponId = couponId;
        return this;
    }

    public String getName() {
        return name;
    }

    public PromotionDetail setName(String name) {
        this.name = name;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public PromotionDetail setScope(String scope) {
        this.scope = scope;
        return this;
    }

    public String getType() {
        return type;
    }

    public PromotionDetail setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public PromotionDetail setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public String getStockId() {
        return stockId;
    }

    public PromotionDetail setStockId(String stockId) {
        this.stockId = stockId;
        return this;
    }

    public Integer getWechatPayContribute() {
        return wechatPayContribute;
    }

    public PromotionDetail setWechatPayContribute(Integer wechatPayContribute) {
        this.wechatPayContribute = wechatPayContribute;
        return this;
    }

    public Integer getMerchantContribute() {
        return merchantContribute;
    }

    public PromotionDetail setMerchantContribute(Integer merchantContribute) {
        this.merchantContribute = merchantContribute;
        return this;
    }

    public Integer getOtherContribute() {
        return otherContribute;
    }

    public PromotionDetail setOtherContribute(Integer otherContribute) {
        this.otherContribute = otherContribute;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public PromotionDetail setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public QueryGoodsDetail getGoodsDetail() {
        return goodsDetail;
    }

    public PromotionDetail setGoodsDetail(QueryGoodsDetail goodsDetail) {
        this.goodsDetail = goodsDetail;
        return this;
    }
}
