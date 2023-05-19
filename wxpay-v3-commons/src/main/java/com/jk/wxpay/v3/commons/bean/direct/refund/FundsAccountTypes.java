package com.jk.wxpay.v3.commons.bean.direct.refund;

/**
 * 退款所使用资金对应的资金账户类型
 */
public enum FundsAccountTypes {

  /**
   * 未结算资金
   */
  UNSETTLED,

  /**
   * 可用余额
   */
  AVAILABLE,

  /**
   * 不可用余额
   */
  UNAVAILABLE,

  /**
   * 运营户
   */
  OPERATION,

  /**
   * 基本账户（含可用余额和不可用余额）
   */
  BASIC
}
