package com.jk.wxpay.v3.block;


import com.jk.wxpay.v3.commons.bean.MerchantPrivateKey;

/**
 * 商户服务私钥管理器。用于管理商户的私钥。
 * 一个service 管理一个商户的私钥。
 */
public interface MerchantPrivateKeyManager {

    /**
     * 获取商户的私钥
     * @return
     */
    MerchantPrivateKey getPrivateKey(String mchId);
    String getApiV3Key(String mchId);
}
