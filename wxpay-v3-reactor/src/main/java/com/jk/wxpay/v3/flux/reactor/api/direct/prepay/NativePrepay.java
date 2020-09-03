package com.jk.wxpay.v3.flux.reactor.api.direct.prepay;

import com.jk.wxpay.v3.commons.bean.direct.PrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.NativePrepayResult;
import com.jk.wxpay.v3.flux.reactor.Constants;
import com.jk.wxpay.v3.flux.reactor.api.direct.Contract;
import com.jk.wxpay.v3.flux.reactor.request.ApiContext;
import com.jk.wxpay.v3.flux.reactor.request.SingleRequester;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class NativePrepay extends SingleRequester<PrepayOrder, NativePrepayResult>
        implements Prepay<PrepayOrder, NativePrepayResult> {

    public NativePrepay(ApiContext apiContext) {
        super(apiContext, Contract.PATH_NATIVE_PREPAY, PrepayOrder.class, NativePrepayResult.class);
    }

    @Override
    public Mono<NativePrepayResult> prepay(PrepayOrder prepayOrder) {
        Map<String, Object> params = new HashMap<>();
        params.put(Constants.JK_MCH_ID, prepayOrder.getMchId());
        return super.post(params, prepayOrder);
    }
}
