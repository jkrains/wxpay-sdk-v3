package com.jk.wxpay.v3.block.api.direct.prepay;

import com.jk.wxpay.v3.block.api.Prepay;
import com.jk.wxpay.v3.block.request.ApiContext;
import com.jk.wxpay.v3.block.request.SingleRequester;
import com.jk.wxpay.v3.commons.bean.direct.PrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.PrepayResult;
import com.jk.wxpay.v3.commons.Constants;


import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class AppPrepay extends SingleRequester<PrepayOrder, PrepayResult> implements Prepay<PrepayOrder, PrepayResult> {

    /**
     * 构造方法。
     *
     * @param apiContext
     */
    public AppPrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_APP_PREPAY, PrepayOrder.class, PrepayResult.class);
    }

    @Override
    public PrepayResult prepay(PrepayOrder prepayOrder) {
        return super.post(null, createHeadersWith(prepayOrder.getMchId()), prepayOrder);
    }
}
