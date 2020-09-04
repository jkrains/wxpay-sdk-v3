package com.jk.wxpay.v3.reactor.api.direct.prepay;

import com.jk.wxpay.v3.commons.bean.direct.PrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.NativePrepayResult;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.reactor.request.ApiContext;
import com.jk.wxpay.v3.reactor.request.SingleRequester;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class NativePrepay extends SingleRequester<PrepayOrder, NativePrepayResult>
        implements Prepay<PrepayOrder, NativePrepayResult> {

    public NativePrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_NATIVE_PREPAY, PrepayOrder.class, NativePrepayResult.class);
    }

    @Override
    public Mono<NativePrepayResult> prepay(PrepayOrder prepayOrder) {
        return super.post(null, createHeadersWith(prepayOrder.getMchId()), prepayOrder);
    }
}
