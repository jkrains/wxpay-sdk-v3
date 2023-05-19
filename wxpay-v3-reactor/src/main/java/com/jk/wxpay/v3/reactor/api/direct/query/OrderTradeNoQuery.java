package com.jk.wxpay.v3.reactor.api.direct.query;

import com.jk.sdk.commons.reactor.ApiContext;
import com.jk.wxpay.v3.commons.bean.direct.result.OrderQueryResult;
import com.jk.wxpay.v3.commons.Constants;

import com.jk.wxpay.v3.reactor.api.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;
import static com.jk.wxpay.v3.commons.util.RequestUtils.createParamsWith;

/**
 * 通过商户订单号查询
 */
public class OrderTradeNoQuery extends SingleRequester<Void, OrderQueryResult>  {

    /**
     * 构造方法。
     *
     * @param apiContext
     */
    public OrderTradeNoQuery(ApiContext apiContext) {
        super(apiContext, Constants.PATH_ORDER_GET_OUT_TRADE_NO, Void.class, OrderQueryResult.class);
    }

    public Mono<OrderQueryResult> query(String mchId, String id) {
        return super.get("/" + id, createParamsWith(mchId), createHeadersWith(mchId));
    }
}
