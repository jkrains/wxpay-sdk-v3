package com.jk.wxpay.v3.reactor.api.direct.prepay;

import com.jk.wxpay.v3.commons.bean.direct.JSAPIPrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.PrepayResult;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.reactor.api.Prepay;
import com.jk.wxpay.v3.reactor.request.ApiContext;
import com.jk.wxpay.v3.reactor.request.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class JsApiPrepay extends SingleRequester<JSAPIPrepayOrder, PrepayResult>
        implements Prepay<JSAPIPrepayOrder, PrepayResult> {

    /**
     * 构造方法。
     * @param apiContext
     */
    public JsApiPrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_JSAPI_PREPAY, JSAPIPrepayOrder.class, PrepayResult.class);
    }

    @Override
    public Mono<PrepayResult> prepay(JSAPIPrepayOrder prepayOrder) {
        return super.post(null, createHeadersWith(prepayOrder.getMchId()), prepayOrder);
    }
}
