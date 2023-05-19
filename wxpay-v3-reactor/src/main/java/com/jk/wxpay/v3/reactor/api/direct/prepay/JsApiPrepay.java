package com.jk.wxpay.v3.reactor.api.direct.prepay;

import com.jk.sdk.commons.reactor.ApiContext;
import com.jk.wxpay.v3.commons.bean.direct.JSAPIPrepayOrderParams;
import com.jk.wxpay.v3.commons.bean.direct.result.PrepayResult;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.validation.ValidationUtils;
import com.jk.wxpay.v3.reactor.api.Prepay;

import com.jk.wxpay.v3.reactor.api.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class JsApiPrepay extends SingleRequester<JSAPIPrepayOrderParams, PrepayResult>
        implements Prepay<JSAPIPrepayOrderParams, PrepayResult> {

    /**
     * 构造方法。
     * @param apiContext
     */
    public JsApiPrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_JSAPI_PREPAY, JSAPIPrepayOrderParams.class, PrepayResult.class);
    }

    @Override
    public Mono<PrepayResult> prepay(JSAPIPrepayOrderParams prepayOrder) {
        ValidationUtils.validate(prepayOrder);
        return super.post(null, createHeadersWith(prepayOrder.getMchId()), prepayOrder);
    }
}
