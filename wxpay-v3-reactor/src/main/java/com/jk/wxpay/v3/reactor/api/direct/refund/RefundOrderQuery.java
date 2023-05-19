package com.jk.wxpay.v3.reactor.api.direct.refund;

import com.jk.sdk.commons.reactor.ApiContext;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.direct.refund.response.RefundResponse;
import com.jk.wxpay.v3.reactor.api.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class RefundOrderQuery extends SingleRequester<Void, RefundResponse> {

  /**
   *
   * 构造方法。
   *
   * @param apiContext
   *
   */
  public RefundOrderQuery(ApiContext apiContext) {
    super(apiContext, Constants.PATH_REFUND_DOMESTIC_REFUNDS, Void.class, RefundResponse.class);
  }

  /**
   * 查询支付的订单。
   * @param merchantId
   * @param outRefundNo
   * @return
   */
  public Mono<RefundResponse> queryRefund(String merchantId, String outRefundNo) {
    return super.get("/" + outRefundNo, null, createHeadersWith(merchantId));
  }
}
