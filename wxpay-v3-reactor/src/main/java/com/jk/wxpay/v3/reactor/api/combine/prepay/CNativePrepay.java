package com.jk.wxpay.v3.reactor.api.combine.prepay;

import com.jk.sdk.commons.reactor.ApiContext;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.combine.CPrepayOrderParams;
import com.jk.wxpay.v3.commons.bean.direct.result.NativePrepayResult;
import com.jk.wxpay.v3.commons.validation.ValidationUtils;
import com.jk.wxpay.v3.reactor.api.Prepay;

import com.jk.wxpay.v3.reactor.api.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class CNativePrepay extends SingleRequester<CPrepayOrderParams, NativePrepayResult>
        implements Prepay<CPrepayOrderParams, NativePrepayResult> {

    public CNativePrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_COMBINE_NATIVE_PREPAY, CPrepayOrderParams.class, NativePrepayResult.class);
    }

    @Override
    public Mono<NativePrepayResult> prepay(CPrepayOrderParams prepayOrder) {
        ValidationUtils.validate(prepayOrder);
        return super.post(null, createHeadersWith(prepayOrder.getCombineMchId()), prepayOrder);
    }
}
