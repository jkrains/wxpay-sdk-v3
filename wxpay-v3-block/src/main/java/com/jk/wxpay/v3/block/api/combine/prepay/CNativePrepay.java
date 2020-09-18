package com.jk.wxpay.v3.block.api.combine.prepay;

import com.jk.sdk.commons.block.ApiContext;
import com.jk.wxpay.v3.block.api.Prepay;

import com.jk.wxpay.v3.block.api.SingleRequester;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.combine.CPrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.NativePrepayResult;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class CNativePrepay extends SingleRequester<CPrepayOrder, NativePrepayResult>
        implements Prepay<CPrepayOrder, NativePrepayResult> {

    public CNativePrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_COMBINE_NATIVE_PREPAY, CPrepayOrder.class, NativePrepayResult.class);
    }

    @Override
    public NativePrepayResult prepay(CPrepayOrder prepayOrder) {
        return super.post(null, createHeadersWith(prepayOrder.getCombineMchId()), prepayOrder);
    }
}
