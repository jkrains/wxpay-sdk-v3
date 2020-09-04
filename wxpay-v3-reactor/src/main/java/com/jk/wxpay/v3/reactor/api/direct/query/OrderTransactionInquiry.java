package com.jk.wxpay.v3.reactor.api.direct.query;

import com.jk.wxpay.v3.commons.bean.direct.result.OrderQueryResult;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.reactor.request.ApiContext;
import com.jk.wxpay.v3.reactor.request.SingleRequester;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;
import static com.jk.wxpay.v3.commons.util.RequestUtils.createParamsWith;

/**
 * 通过微信支付订单号查询
 */
public class OrderTransactionInquiry extends SingleRequester<Void, OrderQueryResult> implements OrderQuery {

    /**
     * 构造方法。
     *
     * @param apiContext
     */
    public OrderTransactionInquiry(ApiContext apiContext) {
        super(apiContext, Constants.PATH_ORDER_GET_TRANSACTION, Void.class, OrderQueryResult.class);
    }

    @Override
    public Mono<OrderQueryResult> query(String mchId, String id) {
        return super.get("/" + id, createParamsWith(mchId), createHeadersWith(mchId));
    }
}
