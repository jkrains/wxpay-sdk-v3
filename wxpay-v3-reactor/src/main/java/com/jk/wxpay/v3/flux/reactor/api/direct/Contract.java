package com.jk.wxpay.v3.flux.reactor.api.direct;

public interface Contract {
    String PATH_APP_PREPAY = "/v3/pay/transactions/app";
    String PATH_JSAPI_PREPAY = "/v3/pay/transactions/jsapi";
    String PATH_NATIVE_PREPAY = "/v3/pay/transactions/native";
    String PATH_H5_PREPAY = "/v3/pay/transactions/h5";

    String PATH_ORDER_GET_TRANSACTION = "/v3/pay/transactions/id";
    String PATH_ORDER_GET_OUT_TRADE_NO = "/v3/pay/transactions/out-trade-no";
    String PATH_ORDER_CLOSE = "/v3/pay/transactions/out-trade-no";
}
