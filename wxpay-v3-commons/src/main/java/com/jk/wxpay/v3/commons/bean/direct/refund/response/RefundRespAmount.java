package com.jk.wxpay.v3.commons.bean.direct.refund.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.wxpay.v3.commons.bean.direct.refund.RefundAmount;

/**
 *
 * 退款。
 */
public class RefundRespAmount extends RefundAmount {

  /**
   * +
   * 用户现金支付金额，单位为分，只能为整数
   * 示例值：90
   */
  @JsonProperty("payer_total")
  private Integer payerTotal;

  /**
   * +
   * 退款给用户的金额，不包含所有优惠券金额
   * 示例值：90
   */
  @JsonProperty("payer_refund")
  private Integer payerRefund;

  /**
   * +
   * 去掉非充值代金券退款金额后的退款金额，单位为分，退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
   */
  @JsonProperty("settlement_refund")
  private Integer settlementRefund;

  /**
   * +
   * 应结订单金额=订单金额-免充值代金券金额，应结订单金额<=订单金额，单位为分
   */
  @JsonProperty("settlement_total")
  private Integer settlementTotal;

  /**
   * +
   * 优惠退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠，单位为分
   */
  @JsonProperty("discount_refund")
  private Integer discountRefund;

  public RefundRespAmount() {
  }

  public Integer getPayerTotal() {
    return payerTotal;
  }

  public RefundRespAmount setPayerTotal(Integer payerTotal) {
    this.payerTotal = payerTotal;
    return this;
  }

  public Integer getPayerRefund() {
    return payerRefund;
  }

  public RefundRespAmount setPayerRefund(Integer payerRefund) {
    this.payerRefund = payerRefund;
    return this;
  }

  public Integer getSettlementRefund() {
    return settlementRefund;
  }

  public RefundRespAmount setSettlementRefund(Integer settlementRefund) {
    this.settlementRefund = settlementRefund;
    return this;
  }

  public Integer getSettlementTotal() {
    return settlementTotal;
  }

  public RefundRespAmount setSettlementTotal(Integer settlementTotal) {
    this.settlementTotal = settlementTotal;
    return this;
  }

  public Integer getDiscountRefund() {
    return discountRefund;
  }

  public RefundRespAmount setDiscountRefund(Integer discountRefund) {
    this.discountRefund = discountRefund;
    return this;
  }
}
