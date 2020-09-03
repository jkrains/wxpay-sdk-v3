package com.jk.wxpay.v3.flux.service;

import com.jk.wxpay.v3.commons.bean.MerchantPrivateKey;
import com.jk.wxpay.v3.flux.reactor.service.MerchantPrivateKeyManager;
import reactor.core.publisher.Mono;

/**
 * 简单的商户私钥服务，可以从resource 中初始化。
 */
public class SimpleMerchantPrivateKeyService implements MerchantPrivateKeyManager {

    @Override
    public Mono<MerchantPrivateKey> getPrivateKey(String mchId) {
        return null;
    }
}
