package com.jk.wxpay.v3.reactor.service;


import com.jk.wxpay.v3.commons.bean.MerchantInfo;
import com.jk.wxpay.v3.commons.bean.MerchantPrivateKey;
import reactor.core.publisher.Mono;

/**
 * 商户服务私钥管理器。用于管理商户的私钥。
 * 一个service 管理一个商户的私钥。
 */
public interface MerchantPrivateKeyManager {

    /**
     * 获取商户的私钥
     * @return
     */
    Mono<MerchantPrivateKey> getPrivateKey(String mchId);

}
