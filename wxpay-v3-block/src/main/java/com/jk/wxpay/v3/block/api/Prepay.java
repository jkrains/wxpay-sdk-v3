package com.jk.wxpay.v3.block.api;

import com.jk.wxpay.v3.commons.exception.WxPayException;

/**
 * 预支付接口
 */
public interface Prepay<T, R> {
    /**
     * 预支付接口
     * @param t
     * @return
     */
    R prepay(T t) throws WxPayException;
}
