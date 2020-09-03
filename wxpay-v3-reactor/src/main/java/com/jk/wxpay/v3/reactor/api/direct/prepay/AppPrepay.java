package com.jk.wxpay.v3.reactor.api.direct.prepay;

import com.jk.wxpay.v3.commons.bean.direct.PrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.PrepayResult;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.reactor.request.ApiContext;
import com.jk.wxpay.v3.reactor.request.SingleRequester;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class AppPrepay extends SingleRequester<PrepayOrder, PrepayResult> implements Prepay<PrepayOrder, PrepayResult> {

    /**
     * 构造方法。
     *
     * @param apiContext
     */
    public AppPrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_APP_PREPAY, PrepayOrder.class, PrepayResult.class);
    }

    @Override
    public Mono<PrepayResult> prepay(PrepayOrder prepayOrder) {
        Map<String, Object> params = new HashMap<>();
        params.put(Constants.JK_MCH_ID, prepayOrder.getMchId());
        return super.post(params, prepayOrder);
    }
}
