package com.jk.wxpay.v3.commons.bean.direct.refund;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 退款金额信息。
 */
public class RefundAmount {

  /**
   * +
   * 退款金额
   * 退款金额，单位为分，只能为整数，不能超过原订单支付金额。
   */
  @JsonProperty("refund")
  private Integer refund;

  /**
   * -
   * 退款出资账户及金额
   */
  @JsonProperty("from")
  private List<RefundSourceAccount> from;

  /**
   * +
   * 原订单金额
   */
  @JsonProperty("total")
  private Integer total;

  /**
   * +
   * 退款币种
   * 符合ISO 4217标准的三位字母代码，目前只支持人民币：CNY。
   * 示例值：CNY
   */
  @JsonProperty("currency")
  private String currency;

  public RefundAmount() {
  }

  public Integer getRefund() {
    return refund;
  }

  public RefundAmount setRefund(Integer refund) {
    this.refund = refund;
    return this;
  }

  public List<RefundSourceAccount> getFrom() {
    return from;
  }

  public RefundAmount setFrom(List<RefundSourceAccount> from) {
    this.from = from;
    return this;
  }

  public Integer getTotal() {
    return total;
  }

  public RefundAmount setTotal(Integer total) {
    this.total = total;
    return this;
  }

  public String getCurrency() {
    return currency;
  }

  public RefundAmount setCurrency(String currency) {
    this.currency = currency;
    return this;
  }
}
