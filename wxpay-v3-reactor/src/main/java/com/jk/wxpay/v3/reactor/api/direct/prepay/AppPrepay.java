package com.jk.wxpay.v3.reactor.api.direct.prepay;

import com.jk.sdk.commons.reactor.ApiContext;
import com.jk.wxpay.v3.commons.bean.direct.PrepayOrderParams;
import com.jk.wxpay.v3.commons.bean.direct.result.PrepayResult;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.validation.ValidationUtils;
import com.jk.wxpay.v3.reactor.api.Prepay;

import com.jk.wxpay.v3.reactor.api.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class AppPrepay extends SingleRequester<PrepayOrderParams, PrepayResult> implements Prepay<PrepayOrderParams, PrepayResult> {

    /**
     * 构造方法。
     *
     * @param apiContext
     */
    public AppPrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_APP_PREPAY, PrepayOrderParams.class, PrepayResult.class);
    }

    @Override
    public Mono<PrepayResult> prepay(PrepayOrderParams prepayOrder) {
        ValidationUtils.validate(prepayOrder);
        return super.post(null, createHeadersWith(prepayOrder.getMchId()), prepayOrder);
    }
}
