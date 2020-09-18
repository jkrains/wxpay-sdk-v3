package com.jk.wxpay.v3.block.api.combine.prepay;

import com.jk.sdk.commons.block.ApiContext;
import com.jk.wxpay.v3.block.api.Prepay;

import com.jk.wxpay.v3.block.api.SingleRequester;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.combine.CPrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.H5PrepayResult;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class CH5Prepay extends SingleRequester<CPrepayOrder, H5PrepayResult>
        implements Prepay<CPrepayOrder, H5PrepayResult> {

    public CH5Prepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_COMBINE_H5_PREPAY, CPrepayOrder.class, H5PrepayResult.class);
    }

    @Override
    public H5PrepayResult prepay(CPrepayOrder prepayOrder) {
        return super.post(null, createHeadersWith(prepayOrder.getCombineMchId()), prepayOrder);
    }
}