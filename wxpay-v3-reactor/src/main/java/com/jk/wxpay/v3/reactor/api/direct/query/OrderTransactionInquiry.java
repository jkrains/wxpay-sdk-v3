package com.jk.wxpay.v3.reactor.api.direct.query;

import com.jk.wxpay.v3.commons.bean.direct.result.OrderQueryResult;
import com.jk.wxpay.v3.reactor.Constants;
import com.jk.wxpay.v3.reactor.api.direct.Contract;
import com.jk.wxpay.v3.reactor.request.ApiContext;
import com.jk.wxpay.v3.reactor.request.SingleRequester;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

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
        super(apiContext, Contract.PATH_ORDER_GET_TRANSACTION, Void.class, OrderQueryResult.class);
    }

    @Override
    public Mono<OrderQueryResult> query(String mchId, String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("mchid", mchId);
        params.put(Constants.JK_MCH_ID, mchId);
        return super.get("/" + id, params);
    }
}
