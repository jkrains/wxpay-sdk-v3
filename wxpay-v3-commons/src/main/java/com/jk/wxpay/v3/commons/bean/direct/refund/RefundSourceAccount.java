package com.jk.wxpay.v3.commons.bean.direct.refund;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 退款出资账户信息。
 */
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class RefundSourceAccount {

  /**
   * +
   * 出资账户类型
   *
   * 下面枚举值多选一。
   * 枚举值：
   * AVAILABLE : 可用余额
   * UNAVAILABLE : 不可用余额
   * 示例值：AVAILABLE
   */
  @JsonProperty("account")
  private String account;

  /**
   * +
   *
   * 对应账户出资金额
   * 示例值：444
   */
  @JsonProperty("amount")
  private Integer amount;

  public RefundSourceAccount(String account, Integer amount) {
    this.account = account;
    this.amount = amount;
  }

  public RefundSourceAccount() {
  }

  public String getAccount() {
    return account;
  }

  public RefundSourceAccount setAccount(String account) {
    this.account = account;
    return this;
  }

  public Integer getAmount() {
    return amount;
  }

  public RefundSourceAccount setAmount(Integer amount) {
    this.amount = amount;
    return this;
  }
}
