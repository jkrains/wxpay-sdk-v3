package com.jk.wxpay.v3.commons.bean.direct.refund;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.wxpay.v3.commons.bean.direct.GoodsDetail;

public class RefundGoodsDetail extends GoodsDetail {

  /**
   * +
   * 商品退款金额
   */
  @JsonProperty("refund_amount")
  private Integer refundAmount;

  /**
   * +
   * 商品退货数量
   */
  @JsonProperty("refund_quantity")
  private Integer refundQuantity;

  public RefundGoodsDetail() {
  }

  public Integer getRefundAmount() {
    return refundAmount;
  }

  public RefundGoodsDetail setRefundAmount(Integer refundAmount) {
    this.refundAmount = refundAmount;
    return this;
  }

  public Integer getRefundQuantity() {
    return refundQuantity;
  }

  public RefundGoodsDetail setRefundQuantity(Integer refundQuantity) {
    this.refundQuantity = refundQuantity;
    return this;
  }
}
