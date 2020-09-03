package com.jk.wxpay.v3.flux.service;

import com.jk.wxpay.v3.reactor.service.WxCertificatesManager;
import reactor.core.publisher.Mono;

import java.security.cert.X509Certificate;

/**
 * 简单的微信公钥服务。
 */
public class SimpleWxCertificatesService implements WxCertificatesManager {

    @Override
    public Mono<X509Certificate> getValidCertificate(String mchId) {
        return null;
    }
}
