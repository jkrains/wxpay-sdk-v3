package com.jk.wxpay.v3.reactor.api.direct.query;

import com.jk.wxpay.v3.commons.bean.RequestParams;
import com.jk.wxpay.v3.commons.util.StringUtils;

/**
 * 查询参数
 * outTradeNo 和 transactionNo 只能有一个生效。
 */
public class QueryParams implements RequestParams {

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 微信交易订单号
     */
    private String transactionNo;

    public QueryParams() {
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public QueryParams setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public QueryParams setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
        return this;
    }

    @Override
    public void validate() throws RuntimeException {
        if (StringUtils.isNullOrEmpty(outTradeNo) && StringUtils.isNullOrEmpty(transactionNo)) {
            throw new IllegalArgumentException("outTradeNo and transactionNo are null or empty");
        } else if (!StringUtils.isNullOrEmpty(outTradeNo) && !StringUtils.isNullOrEmpty(transactionNo)) {
            throw new IllegalArgumentException("outTradeNo and transactionNo have data, only outTradeNo valid or transactionNo valid");
        }
    }
}
