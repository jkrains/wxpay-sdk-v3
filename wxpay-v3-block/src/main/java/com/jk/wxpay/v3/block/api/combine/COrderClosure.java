package com.jk.wxpay.v3.block.api.combine;

import com.jk.wxpay.v3.block.request.ApiContext;
import com.jk.wxpay.v3.block.request.SingleRequester;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.combine.SubOrderCloseInfo;
import com.jk.wxpay.v3.commons.exception.WxErrorException;

import static com.jk.wxpay.v3.commons.util.RequestUtils.createHeadersWith;
import static com.jk.wxpay.v3.commons.util.RequestUtils.createParamsWith;

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
    public void close(String mchid, String outTradeNo, SubOrderCloseInfo subOrderCloseInfo) throws WxErrorException {
        String subPath = "/" + outTradeNo + "/close";
        super.post(subPath, createParamsWith(mchid), createHeadersWith(mchid), subOrderCloseInfo);
    }
}

