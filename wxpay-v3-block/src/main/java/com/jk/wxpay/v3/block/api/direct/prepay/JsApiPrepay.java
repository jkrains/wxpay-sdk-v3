package com.jk.wxpay.v3.block.api.direct.prepay;

import com.jk.sdk.commons.block.ApiContext;
import com.jk.wxpay.v3.block.api.Prepay;

import com.jk.wxpay.v3.block.api.SingleRequester;
import com.jk.wxpay.v3.commons.bean.direct.JSAPIPrepayOrderParams;
import com.jk.wxpay.v3.commons.bean.direct.result.PrepayResult;
import com.jk.wxpay.v3.commons.Constants;


import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

public class JsApiPrepay extends SingleRequester<JSAPIPrepayOrderParams, PrepayResult>
        implements Prepay<JSAPIPrepayOrderParams, PrepayResult> {

    /**
     * 构造方法。
     *
     * @param apiContext

     */
    public JsApiPrepay(ApiContext apiContext) {
        super(apiContext, Constants.PATH_JSAPI_PREPAY, JSAPIPrepayOrderParams.class, PrepayResult.class);
    }

    @Override
    public PrepayResult prepay(JSAPIPrepayOrderParams prepayOrder) {
        return super.post(null, createHeadersWith(prepayOrder.getMchId()), prepayOrder);
    }
}
