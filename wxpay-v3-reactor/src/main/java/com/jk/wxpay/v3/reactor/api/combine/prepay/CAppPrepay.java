package com.jk.wxpay.v3.reactor.api.combine.prepay;

import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.combine.CPrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.PrepayResult;
import com.jk.wxpay.v3.reactor.api.Prepay;
import com.jk.wxpay.v3.reactor.request.ApiContext;
import com.jk.wxpay.v3.reactor.request.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class CAppPrepay extends SingleRequester<CPrepayOrder, PrepayResult>
        implements Prepay<CPrepayOrder, PrepayResult> {

    /**
     * 构造方法。
     *
     * @param apiContext
     */
    public CAppPrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_COMBINE_APP_PREPAY, CPrepayOrder.class, PrepayResult.class);
    }

    @Override
    public Mono<PrepayResult> prepay(CPrepayOrder cPrepayOrder) {
        return super.post(null, createHeadersWith(cPrepayOrder.getCombineMchId()), cPrepayOrder);
    }
}
