package com.jk.wxpay.v3.block.api.direct.query;

import com.jk.wxpay.v3.block.request.ApiContext;
import com.jk.wxpay.v3.block.request.SingleRequester;
import com.jk.wxpay.v3.commons.bean.direct.result.OrderQueryResult;
import com.jk.wxpay.v3.commons.Constants;


import java.util.HashMap;
import java.util.Map;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;
import static com.jk.wxpay.v3.commons.util.RequestUtils.createParamsWith;

/**
 * 通过商户订单号查询
 */
public class OrderTradeNoInquiry extends SingleRequester<Void, OrderQueryResult> implements OrderQuery {

    /**
     * 构造方法。
     *
     * @param apiContext
     */
    public OrderTradeNoInquiry(ApiContext apiContext) {
        super(apiContext, Constants.PATH_ORDER_GET_OUT_TRADE_NO, Void.class, OrderQueryResult.class);
    }

    @Override
    public OrderQueryResult query(String mchId, String id) {
        return super.get("/" + id, createParamsWith(mchId), createHeadersWith(mchId));
    }
}
