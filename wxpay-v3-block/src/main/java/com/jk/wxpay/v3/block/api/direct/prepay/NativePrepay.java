package com.jk.wxpay.v3.block.api.direct.prepay;

import com.jk.sdk.commons.block.ApiContext;
import com.jk.wxpay.v3.block.api.Prepay;
import com.jk.wxpay.v3.block.api.SingleRequester;
import com.jk.wxpay.v3.commons.bean.direct.PrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.NativePrepayResult;
import com.jk.wxpay.v3.commons.Constants;


import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class NativePrepay extends SingleRequester<PrepayOrder, NativePrepayResult>
        implements Prepay<PrepayOrder, NativePrepayResult> {

    public NativePrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_NATIVE_PREPAY, PrepayOrder.class, NativePrepayResult.class);
    }

    @Override
    public NativePrepayResult prepay(PrepayOrder prepayOrder) {
        return super.post(null, createHeadersWith(prepayOrder.getMchId()), prepayOrder);
    }
}
