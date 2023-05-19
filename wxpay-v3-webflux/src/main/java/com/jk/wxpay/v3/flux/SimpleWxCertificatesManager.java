package com.jk.wxpay.v3.flux;

import com.jk.wxpay.v3.commons.exception.WxErrorException;
import com.jk.wxpay.v3.commons.util.PemUtils;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 简单的微信公钥管理器，从resource中加载。
 * 这个类只能用于测试，在正式环境下，不能应用该类。因为微信的证书会动态更新。
 * 需要写一个动态更新的代码，来动态的从微信官方网站获取证书存储在本地。
 */
public class SimpleWxCertificatesManager implements WxCertificatesManager {

    private String certificatesPath = "pem/wx_cert.pem";
    private X509Certificate x509Certificate;

    public SimpleWxCertificatesManager() {
    }

    public void setCertificatesPath(String certificatesPath) {
        this.certificatesPath = certificatesPath;
    }

    @Override
    public Mono<X509Certificate> getValidCertificate(String mchId) {

        try {
            if (this.x509Certificate == null) {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(certificatesPath);
                this.x509Certificate = PemUtils.loadCertificate(inputStream);
            }
            return Mono.just(this.x509Certificate);
        } catch (CertificateException | IllegalArgumentException e) {
            return Mono.error(new WxErrorException(e.getClass().getSimpleName(), e.getMessage()));
        }
    }
}
