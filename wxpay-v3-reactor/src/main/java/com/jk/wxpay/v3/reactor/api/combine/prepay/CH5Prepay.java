package com.jk.wxpay.v3.reactor.api.combine.prepay;

import com.jk.sdk.commons.reactor.ApiContext;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.combine.CPrepayOrderParams;
import com.jk.wxpay.v3.commons.bean.direct.result.H5PrepayResult;
import com.jk.wxpay.v3.commons.validation.ValidationUtils;
import com.jk.wxpay.v3.reactor.api.Prepay;

import com.jk.wxpay.v3.reactor.api.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class CH5Prepay extends SingleRequester<CPrepayOrderParams, H5PrepayResult>
        implements Prepay<CPrepayOrderParams, H5PrepayResult> {

    public CH5Prepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_COMBINE_H5_PREPAY, CPrepayOrderParams.class, H5PrepayResult.class);
    }

    @Override
    public Mono<H5PrepayResult> prepay(CPrepayOrderParams prepayOrder) {
        ValidationUtils.validate(prepayOrder);
        return super.post(null, createHeadersWith(prepayOrder.getCombineMchId()), prepayOrder);
    }
}