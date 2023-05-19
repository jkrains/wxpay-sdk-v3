package com.jk.wxpay.v3.block.api.combine.prepay;

import com.jk.sdk.commons.block.ApiContext;
import com.jk.wxpay.v3.block.api.Prepay;

import com.jk.wxpay.v3.block.api.SingleRequester;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.combine.CPrepayOrderParams;
import com.jk.wxpay.v3.commons.bean.direct.result.H5PrepayResult;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class CH5Prepay extends SingleRequester<CPrepayOrderParams, H5PrepayResult>
        implements Prepay<CPrepayOrderParams, H5PrepayResult> {

    public CH5Prepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_COMBINE_H5_PREPAY, CPrepayOrderParams.class, H5PrepayResult.class);
    }

    @Override
    public H5PrepayResult prepay(CPrepayOrderParams prepayOrder) {
        return super.post(null, createHeadersWith(prepayOrder.getCombineMchId()), prepayOrder);
    }
}