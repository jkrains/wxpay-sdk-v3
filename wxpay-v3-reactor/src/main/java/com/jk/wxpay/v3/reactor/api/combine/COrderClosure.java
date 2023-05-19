package com.jk.wxpay.v3.reactor.api.combine;

import com.jk.sdk.commons.reactor.ApiContext;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.combine.SubOrderCloseParams;
import com.jk.wxpay.v3.commons.validation.ValidationUtils;
import com.jk.wxpay.v3.reactor.api.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;

/**
 * 合单关闭订单API。
 */
public class COrderClosure extends SingleRequester<SubOrderCloseParams, Void> {

    /**
     * 构造方法。
     *
     * @param apiContext
     */
    public COrderClosure(ApiContext apiContext) {
        super(apiContext, Constants.PATH_COMBINE_ORDER_CLOSE, SubOrderCloseParams.class, Void.class);
    }

    /**
     * 调用该接口关闭订单。
     * @param mchId  商户号
     * @param outTradeNo  商户订单号。
     * @param subOrderCloseParams 需要关闭得子订单
     * @return
     */
    public Mono<Void> close(String mchId, String outTradeNo, SubOrderCloseParams subOrderCloseParams) {

        ValidationUtils.required("mchId", mchId);
        ValidationUtils.required("outTradeNo", outTradeNo);
        ValidationUtils.required("subOrderCloseParams", subOrderCloseParams);
        ValidationUtils.validate(subOrderCloseParams);

        String subPath = "/" + outTradeNo + "/close";
        return super.post(subPath, null, createHeadersWith(mchId), subOrderCloseParams);
    }
}
