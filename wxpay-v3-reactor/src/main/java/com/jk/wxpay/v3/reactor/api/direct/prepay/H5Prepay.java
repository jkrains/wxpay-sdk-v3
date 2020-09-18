package com.jk.wxpay.v3.reactor.api.direct.prepay;

import com.jk.sdk.commons.reactor.ApiContext;
import com.jk.wxpay.v3.commons.bean.direct.PrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.H5PrepayResult;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.reactor.api.Prepay;
import com.jk.wxpay.v3.reactor.api.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class H5Prepay extends SingleRequester<PrepayOrder, H5PrepayResult> implements Prepay<PrepayOrder, H5PrepayResult> {

    public H5Prepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_H5_PREPAY, PrepayOrder.class, H5PrepayResult.class);
    }

    @Override
    public Mono<H5PrepayResult> prepay(PrepayOrder prepayOrder) {
        return super.post(null, createHeadersWith(prepayOrder.getMchId()), prepayOrder);
    }
}
