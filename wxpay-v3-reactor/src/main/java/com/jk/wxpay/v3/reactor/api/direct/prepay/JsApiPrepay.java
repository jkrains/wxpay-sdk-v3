package com.jk.wxpay.v3.reactor.api.direct.prepay;

import com.jk.wxpay.v3.commons.bean.direct.JSAPIPrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.PrepayResult;
import com.jk.wxpay.v3.reactor.Constants;
import com.jk.wxpay.v3.reactor.api.direct.Contract;
import com.jk.wxpay.v3.reactor.request.ApiContext;
import com.jk.wxpay.v3.reactor.request.SingleRequester;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class JsApiPrepay extends SingleRequester<JSAPIPrepayOrder, PrepayResult>
        implements Prepay<JSAPIPrepayOrder, PrepayResult> {

    /**
     * 构造方法。
     *
     * @param apiContext

     */
    public JsApiPrepay(ApiContext apiContext) {
        super(apiContext, Contract.PATH_JSAPI_PREPAY, JSAPIPrepayOrder.class, PrepayResult.class);
    }

    @Override
    public Mono<PrepayResult> prepay(JSAPIPrepayOrder prepayOrder) {
        Map<String, Object> params = new HashMap<>();
        params.put(Constants.JK_MCH_ID, prepayOrder.getMchId());
        return super.post(params, prepayOrder);
    }
}
