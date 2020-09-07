package com.jk.wxpay.v3.reactor.api.combine.prepay;

import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.combine.CPrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.PrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.NativePrepayResult;
import com.jk.wxpay.v3.reactor.api.Prepay;
import com.jk.wxpay.v3.reactor.request.ApiContext;
import com.jk.wxpay.v3.reactor.request.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class CNativePrepay extends SingleRequester<CPrepayOrder, NativePrepayResult>
        implements Prepay<CPrepayOrder, NativePrepayResult> {

    public CNativePrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_COMBINE_NATIVE_PREPAY, CPrepayOrder.class, NativePrepayResult.class);
    }

    @Override
    public Mono<NativePrepayResult> prepay(CPrepayOrder prepayOrder) {
        return super.post(null, createHeadersWith(prepayOrder.getCombineMchId()), prepayOrder);
    }
}
