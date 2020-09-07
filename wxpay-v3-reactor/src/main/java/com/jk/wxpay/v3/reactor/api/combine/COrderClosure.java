package com.jk.wxpay.v3.reactor.api.combine;

import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.combine.SubOrderCloseInfo;
import com.jk.wxpay.v3.commons.bean.direct.MerchantId;
import com.jk.wxpay.v3.reactor.request.ApiContext;
import com.jk.wxpay.v3.reactor.request.SingleRequester;
import reactor.core.publisher.Mono;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;
import static com.jk.wxpay.v3.commons.util.RequestUtils.createParamsWith;

/**
 * 合单关闭订单API。
 */
public class COrderClosure extends SingleRequester<SubOrderCloseInfo, Void> {

    /**
     * 构造方法。
     *
     * @param apiContext
     */
    public COrderClosure(ApiContext apiContext) {
        super(apiContext, Constants.PATH_COMBINE_ORDER_CLOSE, SubOrderCloseInfo.class, Void.class);
    }

    /**
     * 调用该接口关闭订单。
     * @param mchid  商户号
     * @param outTradeNo  商户订单号。
     * @param subOrderCloseInfo 需要关闭得子订单
     * @return
     */
    public Mono<Void> close(String mchid, String outTradeNo, SubOrderCloseInfo subOrderCloseInfo) {
        String subPath = "/" + outTradeNo + "/close";
        return super.post(subPath, createParamsWith(mchid), createHeadersWith(mchid), subOrderCloseInfo);
    }
}
