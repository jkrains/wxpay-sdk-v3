package com.jk.wxpay.v3.reactor.api.combine.prepay;

import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.combine.CPrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.PrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.H5PrepayResult;
import com.jk.wxpay.v3.reactor.api.Prepay;
import com.jk.wxpay.v3.reactor.request.ApiContext;
import com.jk.wxpay.v3.reactor.request.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class CH5Prepay extends SingleRequester<CPrepayOrder, H5PrepayResult>
        implements Prepay<CPrepayOrder, H5PrepayResult> {

    public CH5Prepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_COMBINE_H5_PREPAY, CPrepayOrder.class, H5PrepayResult.class);
    }

    @Override
    public Mono<H5PrepayResult> prepay(CPrepayOrder prepayOrder) {
        return super.post(null, createHeadersWith(prepayOrder.getCombineMchId()), prepayOrder);
    }
}