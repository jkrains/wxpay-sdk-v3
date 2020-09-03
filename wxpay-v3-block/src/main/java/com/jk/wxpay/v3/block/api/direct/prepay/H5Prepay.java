package com.jk.wxpay.v3.block.api.direct.prepay;

import com.jk.wxpay.v3.block.request.ApiContext;
import com.jk.wxpay.v3.block.request.SingleRequester;
import com.jk.wxpay.v3.commons.bean.direct.PrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.H5PrepayResult;
import com.jk.wxpay.v3.commons.Constants;

import java.util.HashMap;
import java.util.Map;

public class H5Prepay extends SingleRequester<PrepayOrder, H5PrepayResult> implements Prepay<PrepayOrder, H5PrepayResult> {

    public H5Prepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_H5_PREPAY, PrepayOrder.class, H5PrepayResult.class);
    }

    @Override
    public H5PrepayResult prepay(PrepayOrder prepayOrder) {
        Map<String, Object> params = new HashMap<>();
        params.put(Constants.JK_MCH_ID, prepayOrder.getMchId());
        return super.post(params, prepayOrder);
    }
}
