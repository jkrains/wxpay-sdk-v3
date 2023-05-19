package com.jk.wxpay.v3.flux;

import reactor.core.publisher.Mono;
import java.security.cert.X509Certificate;

/**
 * 这是一个证书管理器， 管理微信支付平台的公钥。
 * 可以根据证书编号，来获取。 后续需要扩展，如果证书过期则需要从证书管理器中删除证书。
 * 一个Service 管理一个商户。
 */
public interface WxCertificatesManager {

    /**
     * 获取有效的证书信息。
     * @return
     */
    Mono<X509Certificate> getValidCertificate(String mchId);
}
