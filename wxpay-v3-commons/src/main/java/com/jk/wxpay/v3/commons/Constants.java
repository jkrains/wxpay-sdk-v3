package com.jk.wxpay.v3.commons;

/**
 * 定义了一些常用的常量
 */
public interface Constants {

    String PATH_APP_PREPAY = "/v3/pay/transactions/app";
    String PATH_JSAPI_PREPAY = "/v3/pay/transactions/jsapi";
    String PATH_NATIVE_PREPAY = "/v3/pay/transactions/native";
    String PATH_H5_PREPAY = "/v3/pay/transactions/h5";

    String PATH_ORDER_GET_TRANSACTION = "/v3/pay/transactions/id";
    String PATH_ORDER_GET_OUT_TRADE_NO = "/v3/pay/transactions/out-trade-no";
    String PATH_ORDER_CLOSE = "/v3/pay/transactions/out-trade-no";

    /**
     * 微信支付基本域名后缀
     */
    String WX_BASE_URL_SUFFIX = ".mch.weixin.qq.com";
    /**
     * 即刻易用自定义 header, 用于应用与拦截器之间传递信息。
     */
    String JK_MCH_ID = "jk_mchId";
}
