package com.jk.wxpay.v3.reactor.api.direct.refund;

import com.jk.sdk.commons.reactor.ApiContext;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.direct.refund.RefundParams;
import com.jk.wxpay.v3.commons.bean.direct.refund.response.RefundResponse;
import com.jk.wxpay.v3.reactor.api.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class OrderRefund extends SingleRequester<RefundParams, RefundResponse> {

  public OrderRefund(ApiContext apiContext) {
    super(apiContext, Constants.PATH_REFUND_DOMESTIC_REFUNDS, RefundParams.class, RefundResponse.class);
  }

  /**
   *
   * 退款
   *
   * @param merchantId
   * @param params
   * @return
   */
  public Mono<RefundResponse> refund(String merchantId, RefundParams params) {
    return super.post(null, createHeadersWith(merchantId), params);
  }
}
