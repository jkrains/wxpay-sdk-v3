package com.jk.wxpay.v3.block.api.combine.query;

import com.jk.sdk.commons.block.ApiContext;
import com.jk.wxpay.v3.block.api.SingleRequester;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.combine.result.COrderQueryResult;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;
import static com.jk.wxpay.v3.commons.util.RequestUtils.createParamsWith;

public class COrderInquiry extends SingleRequester<Void, COrderQueryResult> {

    /**
     * 构造方法。
     * @param apiContext
     */
    public COrderInquiry(ApiContext apiContext) {
        super(apiContext, Constants.PATH_COMBINE_ORDER_GET_OUT_TRADE_NO, Void.class, COrderQueryResult.class);
    }

    public COrderQueryResult query(String mchId, String id) {
        return super.get("/" + id, createParamsWith(mchId), createHeadersWith(mchId));
    }
}