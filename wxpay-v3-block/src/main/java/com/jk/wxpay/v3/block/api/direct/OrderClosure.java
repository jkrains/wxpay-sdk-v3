package com.jk.wxpay.v3.block.api.direct;

import com.jk.sdk.commons.block.ApiContext;
import com.jk.wxpay.v3.block.api.SingleRequester;
import com.jk.wxpay.v3.commons.bean.direct.MerchantId;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.exception.WxErrorException;
import com.jk.wxpay.v3.commons.util.RequestUtils;

/**
 *
 * 订单关闭对象，调用该对象进行订单的关闭
 * 以下情况需要调用关单接口：商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，
 * 避免重复支付；系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
 *
 *
 * 注意：
 * • 订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
 */
public class OrderClosure extends SingleRequester<MerchantId, Void> {

    /**
     * 构造方法。
     *
     * @param apiContext
     */
    public OrderClosure(ApiContext apiContext) {
        super(apiContext, Constants.PATH_ORDER_CLOSE, MerchantId.class, Void.class);
    }

    /**
     * 调用该接口关闭订单。
     * @param mchid  商户号
     * @param outTradeNo  商户订单号。
     * @return
     */
    public void close(String mchid, String outTradeNo) throws WxErrorException {
        String subPath = "/" + outTradeNo + "/close";
        super.post(subPath, RequestUtils.createParamsWith(mchid),
                RequestUtils.createHeadersWith(mchid), new MerchantId(mchid));
    }
}
