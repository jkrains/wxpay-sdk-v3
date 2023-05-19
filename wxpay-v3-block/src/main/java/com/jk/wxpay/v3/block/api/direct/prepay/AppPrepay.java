package com.jk.wxpay.v3.block.api.direct.prepay;

import com.jk.sdk.commons.block.ApiContext;
import com.jk.wxpay.v3.block.api.Prepay;

import com.jk.wxpay.v3.block.api.SingleRequester;
import com.jk.wxpay.v3.commons.bean.direct.PrepayOrderParams;
import com.jk.wxpay.v3.commons.bean.direct.result.PrepayResult;
import com.jk.wxpay.v3.commons.Constants;


import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class AppPrepay extends SingleRequester<PrepayOrderParams, PrepayResult> implements Prepay<PrepayOrderParams, PrepayResult> {

    /**
     * 构造方法。
     *
     * @param apiContext
     */
    public AppPrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_APP_PREPAY, PrepayOrderParams.class, PrepayResult.class);
    }

    @Override
    public PrepayResult prepay(PrepayOrderParams prepayOrder) {
        return super.post(null, createHeadersWith(prepayOrder.getMchId()), prepayOrder);
    }
}
