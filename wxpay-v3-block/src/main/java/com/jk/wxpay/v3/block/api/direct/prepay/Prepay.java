package com.jk.wxpay.v3.block.api.direct.prepay;

/**
 * 预支付接口
 */
public interface Prepay<T, R> {
    /**
     * 预支付接口
     * @param t
     * @return
     */
    R prepay(T t);
}
