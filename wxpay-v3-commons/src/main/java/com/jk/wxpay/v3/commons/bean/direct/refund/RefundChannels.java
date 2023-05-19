package com.jk.wxpay.v3.commons.bean.direct.refund;

/**
 *
 * 退款渠道。
 */
public enum RefundChannels {

  /**
   * 原路退款
   */
  ORIGINAL,

  /**
   * 退回到余额
   */
  BALANCE,

  /**
   * 原账户异常退到其他余额账户
   */
  OTHER_BALANCE,

  /**
   * OTHER_BANKCARD
   */
  OTHER_BANKCARD
}
