package com.jk.wxpay.v3.block.api.direct.prepay;

import com.jk.sdk.commons.block.ApiContext;
import com.jk.wxpay.v3.block.api.Prepay;

import com.jk.wxpay.v3.block.api.SingleRequester;
import com.jk.wxpay.v3.commons.bean.direct.PrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.H5PrepayResult;
import com.jk.wxpay.v3.commons.Constants;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class H5Prepay extends SingleRequester<PrepayOrder, H5PrepayResult> implements Prepay<PrepayOrder, H5PrepayResult> {

    public H5Prepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_H5_PREPAY, PrepayOrder.class, H5PrepayResult.class);
    }

    @Override
    public H5PrepayResult prepay(PrepayOrder prepayOrder) {
        return super.post(null, createHeadersWith(prepayOrder.getMchId()), prepayOrder);
    }
}
