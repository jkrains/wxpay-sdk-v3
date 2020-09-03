package com.jk.wxpay.v3.reactor.api.direct.prepay;

import com.jk.wxpay.v3.commons.bean.direct.PrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.H5PrepayResult;
import com.jk.wxpay.v3.reactor.Constants;
import com.jk.wxpay.v3.reactor.api.direct.Contract;
import com.jk.wxpay.v3.reactor.request.ApiContext;
import com.jk.wxpay.v3.reactor.request.SingleRequester;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class H5Prepay extends SingleRequester<PrepayOrder, H5PrepayResult> implements Prepay<PrepayOrder, H5PrepayResult> {

    public H5Prepay(ApiContext apiContext) {
        super(apiContext, Contract.PATH_H5_PREPAY, PrepayOrder.class, H5PrepayResult.class);
    }

    @Override
    public Mono<H5PrepayResult> prepay(PrepayOrder prepayOrder) {
        Map<String, Object> params = new HashMap<>();
        params.put(Constants.JK_MCH_ID, prepayOrder.getMchId());
        return super.post(params, prepayOrder);
    }
}
