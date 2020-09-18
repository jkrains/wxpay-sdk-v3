package com.jk.wxpay.v3.reactor.api.direct.query;

import com.jk.sdk.commons.reactor.ApiContext;
import com.jk.wxpay.v3.commons.bean.direct.result.OrderQueryResult;
import reactor.core.publisher.Mono;

/**
 * 提供两种查询订单的方法。
 * 1. 通过微信交易号查询。
 * 2. 通过商户订单号查询。
 *
 * 商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。查询订单状态可通过微信支付订单号或商户订单号两种方式查询
 *
 *
 * 注意：
 * 查询订单可通过微信支付订单号和商户订单号两种方式查询，两种查询方式返回结果相同
 *
 *
 * 需要调用查询接口的情况：
 *
 * • 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知。
 *
 * • 调用支付接口后，返回系统错误或未知交易状态情况。
 *
 * • 调用付款码支付API，返回USERPAYING的状态。
 *
 * • 调用关单或撤销接口API之前，需确认支付状态。
 */
public class OrderInquiry {

    private ApiContext apiContext;
    private OrderQuery orderTradeNoInquiry;
    private OrderQuery orderTransactionInquiry;

    public OrderInquiry(ApiContext apiContext) {
        this.apiContext = apiContext;
    }

    /**
     * 通过商户订单号查询。
     * @param mchId
     * @param tradeNo
     * @return
     */
    public Mono<OrderQueryResult> queryByOutTradeNo(String mchId, String tradeNo) {
        if (this.orderTradeNoInquiry == null) {
            this.orderTradeNoInquiry = new OrderTradeNoInquiry(this.apiContext);
        }
        return this.orderTradeNoInquiry.query(mchId, tradeNo);
    }

    /**
     * 通过微信交易订单号查询。
     * @param mchId
     * @param transactionId
     * @return
     */
    public Mono<OrderQueryResult> queryByWechatTransactionId(String mchId, String transactionId) {
        if (this.orderTransactionInquiry == null) {
            this.orderTransactionInquiry = new OrderTransactionInquiry(this.apiContext);
        }
        return this.orderTransactionInquiry.query(mchId, transactionId);
    }
}
