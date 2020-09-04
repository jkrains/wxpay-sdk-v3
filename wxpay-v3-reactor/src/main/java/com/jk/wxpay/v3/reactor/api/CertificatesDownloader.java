package com.jk.wxpay.v3.reactor.api;

import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.cert.EncryptCertificateEntity;
import com.jk.wxpay.v3.reactor.request.ApiContext;
import com.jk.wxpay.v3.reactor.request.SingleRequester;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 证书下载器，下载证书。
 * 这个对象用来下载微信端的证书，不需要验签微信端返回的数据。
 */
public class CertificatesDownloader extends SingleRequester<Void, EncryptCertificateEntity> {
    /**
     * 构造方法。
     *
     * @param apiContext
     */
    public CertificatesDownloader(ApiContext apiContext) {
        super(apiContext, Constants.PATH_CERTIFICATES, Void.class, EncryptCertificateEntity.class);
    }

    /**
     * 获取证书实体。
     * @param mchId
     * @return
     */
    public Mono<EncryptCertificateEntity> getCertificates(String mchId) {
        Map<String, Object> params = new HashMap<>();
        params.put(Constants.JK_MCH_ID, mchId);
        return super.get(params);
    }
}
