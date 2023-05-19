package com.jk.wxpay.v3.block.api.combine.prepay;

import com.jk.sdk.commons.block.ApiContext;
import com.jk.wxpay.v3.block.api.Prepay;

import com.jk.wxpay.v3.block.api.SingleRequester;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.combine.CPrepayOrderParams;
import com.jk.wxpay.v3.commons.bean.direct.result.NativePrepayResult;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class CNativePrepay extends SingleRequester<CPrepayOrderParams, NativePrepayResult>
        implements Prepay<CPrepayOrderParams, NativePrepayResult> {

    public CNativePrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_COMBINE_NATIVE_PREPAY, CPrepayOrderParams.class, NativePrepayResult.class);
    }

    @Override
    public NativePrepayResult prepay(CPrepayOrderParams prepayOrder) {
        return super.post(null, createHeadersWith(prepayOrder.getCombineMchId()), prepayOrder);
    }
}
