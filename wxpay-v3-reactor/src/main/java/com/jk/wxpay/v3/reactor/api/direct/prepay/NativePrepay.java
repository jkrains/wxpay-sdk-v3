package com.jk.wxpay.v3.reactor.api.direct.prepay;

import com.jk.sdk.commons.reactor.ApiContext;
import com.jk.wxpay.v3.commons.bean.direct.PrepayOrderParams;
import com.jk.wxpay.v3.commons.bean.direct.result.NativePrepayResult;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.validation.ValidationUtils;
import com.jk.wxpay.v3.reactor.api.Prepay;

import com.jk.wxpay.v3.reactor.api.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class NativePrepay extends SingleRequester<PrepayOrderParams, NativePrepayResult>
        implements Prepay<PrepayOrderParams, NativePrepayResult> {

    public NativePrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_NATIVE_PREPAY, PrepayOrderParams.class, NativePrepayResult.class);
    }

    @Override
    public Mono<NativePrepayResult> prepay(PrepayOrderParams prepayOrder) {
        ValidationUtils.validate(prepayOrder);
        return super.post(null, createHeadersWith(prepayOrder.getMchId()), prepayOrder);
    }
}
