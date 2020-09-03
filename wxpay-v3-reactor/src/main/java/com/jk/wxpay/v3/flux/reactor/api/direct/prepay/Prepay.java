package com.jk.wxpay.v3.flux.reactor.api.direct.prepay;

import reactor.core.publisher.Mono;

/**
 * 预支付接口
 */
public interface Prepay<T, R> {
    /**
     * 预支付接口
     * @param t
     * @return
     */
    Mono<R> prepay(T t);
}
