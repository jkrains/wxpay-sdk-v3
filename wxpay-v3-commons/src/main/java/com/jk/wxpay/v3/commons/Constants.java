package com.jk.wxpay.v3.commons;

/**
 * 定义了一些常用的常量
 */
public interface Constants {
    /**
     * 支付的url.正式。
     */
    String PAY_HOST_URL = "https://api.mch.weixin.qq.com";

    /**
     * 直连支付。
     */
    String PATH_APP_PREPAY = "/v3/pay/transactions/app";
    String PATH_JSAPI_PREPAY = "/v3/pay/transactions/jsapi";
    String PATH_NATIVE_PREPAY = "/v3/pay/transactions/native";
    String PATH_H5_PREPAY = "/v3/pay/transactions/h5";

    String PATH_ORDER_GET_TRANSACTION = "/v3/pay/transactions/id";
    String PATH_ORDER_GET_OUT_TRADE_NO = "/v3/pay/transactions/out-trade-no";
    String PATH_ORDER_CLOSE = "/v3/pay/transactions/out-trade-no";

    /**
     * 合单支付
     */
    /**
     * 直连支付。
     */
    String PATH_COMBINE_APP_PREPAY = "/v3/combine-transactions/app";
    String PATH_COMBINE_JSAPI_PREPAY = "/v3/combine-transactions/jsapi";
    String PATH_COMBINE_NATIVE_PREPAY = "/v3/combine-transactions/native";
    String PATH_COMBINE_H5_PREPAY = "/v3/combine-transactions/h5";
    String PATH_COMBINE_ORDER_GET_OUT_TRADE_NO = "/v3/pay/combine-transactions/out-trade-no";
    String PATH_COMBINE_ORDER_CLOSE = "/v3/pay/combine-transactions/out-trade-no";
    /**
     * wx 证书下载地址。
     */
    String PATH_CERTIFICATES = "/v3/certificates";
    /**
     * 如下定义了微信返回的header内容。
     */
    public static final String H_REQUEST_ID  = "Request-ID";
    public static final String H_W_SERIAL    = "Wechatpay-Serial";
    public static final String H_W_SIGNATURE = "Wechatpay-Signature";
    public static final String H_W_TIMESTAMP = "Wechatpay-Timestamp";
    public static final String H_W_NONCE     = "Wechatpay-Nonce";

    /**
     * 微信支付基本域名后缀
     */
    String WX_BASE_URL_SUFFIX = ".mch.weixin.qq.com";
    /**
     * 即刻易用自定义 header, 用于应用与拦截器之间传递信息。
     */
    String JK_MCH_ID = "jk_mchId";
}
