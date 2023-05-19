package com.jk.wxpay.v3.commons.bean.direct.refund.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 优惠商品发生退款时返回商品信息
 */
public class RefundRespGoodsDetail {

  /**
   * +
   * 由半角的大小写字母、数字、中划线、下划线中的一种或几种组成
   * 示例值：1217752501201407033233368018
   */
  @JsonProperty("merchant_goods_id")
  private String merchantGoodsId;

  /**
   * -
   * 微信支付定义的统一商品编号（没有可不传）
   * 示例值：1001
   */
  @JsonProperty("wechatpay_goods_id")
  private String wechatPayGoodsId;

  /**
   * -
   * 商品的实际名称
   * 示例值：iPhone6s 16G
   */
  @JsonProperty("goods_name")
  private String goodsName;

  /**
   * +
   * 商品单价金额，单位为分
   * 示例值：528800
   */
  @JsonProperty("unit_price")
  private Integer unitPrice;

  /**
   * +
   * 商品退款金额，单位为分
   * 示例值：528800
   */
  @JsonProperty("refund_amount")
  private Integer refundAmount;

  /**
   * +
   * 单品的退款数量
   * 示例值：1
   */
  @JsonProperty("refund_quantity")
  private Integer refundQuantity;

  public RefundRespGoodsDetail() {
  }

  public String getMerchantGoodsId() {
    return merchantGoodsId;
  }

  public RefundRespGoodsDetail setMerchantGoodsId(String merchantGoodsId) {
    this.merchantGoodsId = merchantGoodsId;
    return this;
  }

  public String getWechatPayGoodsId() {
    return wechatPayGoodsId;
  }

  public RefundRespGoodsDetail setWechatPayGoodsId(String wechatPayGoodsId) {
    this.wechatPayGoodsId = wechatPayGoodsId;
    return this;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public RefundRespGoodsDetail setGoodsName(String goodsName) {
    this.goodsName = goodsName;
    return this;
  }

  public Integer getUnitPrice() {
    return unitPrice;
  }

  public RefundRespGoodsDetail setUnitPrice(Integer unitPrice) {
    this.unitPrice = unitPrice;
    return this;
  }

  public Integer getRefundAmount() {
    return refundAmount;
  }

  public RefundRespGoodsDetail setRefundAmount(Integer refundAmount) {
    this.refundAmount = refundAmount;
    return this;
  }

  public Integer getRefundQuantity() {
    return refundQuantity;
  }

  public RefundRespGoodsDetail setRefundQuantity(Integer refundQuantity) {
    this.refundQuantity = refundQuantity;
    return this;
  }
}
