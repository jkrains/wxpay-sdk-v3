package com.jk.wxpay.v3.commons.bean.direct.refund.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.wxpay.v3.commons.bean.direct.PromotionScope;
import com.jk.wxpay.v3.commons.bean.direct.PromotionType;

/**
 * 优惠退款信息
 */
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class RefundRespPromotionDetail {

  /**
   * +
   * 券或者立减优惠id
   * 示例值：109519
   */
  @JsonProperty("promotion_id")
  private String promotionId;

  /**
   * +
   * 枚举值：
   * GLOBAL：全场代金券
   * SINGLE：单品优惠
   * 示例值：SINGLE
   */
  @JsonProperty("scope")
  private PromotionScope scope;

  /**
   * +
   * 枚举值：
   * COUPON：代金券，需要走结算资金的充值型代金券
   * DISCOUNT：优惠券，不走结算资金的免充值型优惠券
   * 示例值：DISCOUNT
   */
  @JsonProperty("type")
  private PromotionType type;

  /**
   * +
   * 用户享受优惠的金额（优惠券面额=微信出资金额+商家出资金额+其他出资方金额 ），单位为分
   * 示例值：5
   */
  @JsonProperty("amount")
  private Integer amount;

  /**
   * +
   * 优惠退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为用户支付的现金，说明详见代金券或立减优惠，单位为分
   * 示例值：100
   */
  @JsonProperty("refund_amount")
  private Integer refundAmount;

  /**
   * -
   * 优惠商品发生退款时返回商品信息
   */
  @JsonProperty("goods_detail")
  private RefundRespGoodsDetail goodsDetail;

  public RefundRespPromotionDetail() {
  }

  public String getPromotionId() {
    return promotionId;
  }

  public RefundRespPromotionDetail setPromotionId(String promotionId) {
    this.promotionId = promotionId;
    return this;
  }

  public PromotionScope getScope() {
    return scope;
  }

  public RefundRespPromotionDetail setScope(PromotionScope scope) {
    this.scope = scope;
    return this;
  }

  public PromotionType getType() {
    return type;
  }

  public RefundRespPromotionDetail setType(PromotionType type) {
    this.type = type;
    return this;
  }

  public Integer getAmount() {
    return amount;
  }

  public RefundRespPromotionDetail setAmount(Integer amount) {
    this.amount = amount;
    return this;
  }

  public Integer getRefundAmount() {
    return refundAmount;
  }

  public RefundRespPromotionDetail setRefundAmount(Integer refundAmount) {
    this.refundAmount = refundAmount;
    return this;
  }

  public RefundRespGoodsDetail getGoodsDetail() {
    return goodsDetail;
  }

  public RefundRespPromotionDetail setGoodsDetail(RefundRespGoodsDetail goodsDetail) {
    this.goodsDetail = goodsDetail;
    return this;
  }
}
