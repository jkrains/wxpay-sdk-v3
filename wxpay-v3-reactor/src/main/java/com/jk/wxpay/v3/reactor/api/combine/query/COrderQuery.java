package com.jk.wxpay.v3.reactor.api.combine.query;

import com.jk.sdk.commons.reactor.ApiContext;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.combine.result.COrderQueryResult;

import com.jk.wxpay.v3.commons.validation.ValidationUtils;
import com.jk.wxpay.v3.reactor.api.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;
import static com.jk.wxpay.v3.commons.util.RequestUtils.createParamsWith;

/**
 * 合单查询， 按照订单号查询。
 */
public class COrderQuery extends SingleRequester<Void, COrderQueryResult>  {

    /**
     * 构造方法。
     * @param apiContext
     */
    public COrderQuery(ApiContext apiContext) {
        super(apiContext, Constants.PATH_COMBINE_ORDER_GET_OUT_TRADE_NO, Void.class, COrderQueryResult.class);
    }

    public Mono<COrderQueryResult> query(String mchId, String outTradeNo) {
        ValidationUtils.required("mchId", mchId);
        ValidationUtils.required("outTradeNo", outTradeNo);
        return super.get("/" + outTradeNo, createParamsWith(mchId), createHeadersWith(mchId));
    }
}