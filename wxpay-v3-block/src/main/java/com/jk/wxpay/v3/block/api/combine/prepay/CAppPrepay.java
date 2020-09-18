package com.jk.wxpay.v3.block.api.combine.prepay;

import com.jk.sdk.commons.block.ApiContext;
import com.jk.wxpay.v3.block.api.Prepay;

import com.jk.wxpay.v3.block.api.SingleRequester;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.combine.CPrepayOrder;
import com.jk.wxpay.v3.commons.bean.direct.result.PrepayResult;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class CAppPrepay extends SingleRequester<CPrepayOrder, PrepayResult>
        implements Prepay<CPrepayOrder, PrepayResult> {

    /**
     * 构造方法。
     *
     * @param apiContext
     */
    public CAppPrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_COMBINE_APP_PREPAY, CPrepayOrder.class, PrepayResult.class);
    }

    @Override
    public PrepayResult prepay(CPrepayOrder cPrepayOrder) {
        return super.post(null, createHeadersWith(cPrepayOrder.getCombineMchId()), cPrepayOrder);
    }
}