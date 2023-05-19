package com.jk.wxpay.v3.commons.bean.direct.refund;

/**
 * 退款状态。
 * 退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台-交易中心，手动处理此笔退款。
 */
public enum RefundStatus {
  /**
   * 退款成功
   */
  SUCCESS,

  /**
   * 退款关闭
   */
  CLOSED,

  /**
   * PROCESSING
   */
  PROCESSING,

  /**
   * 退款异常
   */
  ABNORMAL
}
