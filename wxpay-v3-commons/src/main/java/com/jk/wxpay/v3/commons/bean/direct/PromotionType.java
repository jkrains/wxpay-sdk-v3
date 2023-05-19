package com.jk.wxpay.v3.commons.bean.direct;

public enum PromotionType {
  /**
   * 代金券，需要走结算资金的充值型代金券
   */
  COUPON,

  /**
   * 优惠券，不走结算资金的免充值型优惠券
   */
  DISCOUNT
}
