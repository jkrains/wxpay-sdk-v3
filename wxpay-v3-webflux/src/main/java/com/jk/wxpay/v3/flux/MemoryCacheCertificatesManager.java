package com.jk.wxpay.v3.flux;

import com.jk.wxpay.v3.commons.cert.CertificatesDecoder;
import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxPayException;
import com.jk.wxpay.v3.flux.http.ApiContextBuilder;
import com.jk.wxpay.v3.reactor.MerchantPrivateKeyManager;
import com.jk.wxpay.v3.reactor.WxCertificatesManager;
import com.jk.wxpay.v3.reactor.api.CertificatesDownloader;
import com.jk.wxpay.v3.reactor.request.ApiContext;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自动更新证书管理器，更新证书存储在内存中
 */
public class MemoryCacheCertificatesManager implements WxCertificatesManager {

    private final CertificatesDownloader certificatesDownloader;
    private final MerchantPrivateKeyManager privateKeyManager;

    /**
     * 用map来存储密钥。
     */
    private final Map<String, List<X509Certificate>> certMapList = Collections.synchronizedMap(new HashMap<>());

    public MemoryCacheCertificatesManager(MerchantPrivateKeyManager privateKeyManager) {
        this.privateKeyManager = privateKeyManager;
        ApiContext apiContext = new ApiContextBuilder().setMerchantPrivateKeyManager(privateKeyManager).build();
        this.certificatesDownloader = new CertificatesDownloader(apiContext);
    }

    @Override
    public Mono<X509Certificate> getValidCertificate(String mchId) {
        /**
         * 获取证书，解析证书需要时间，我们放到子线程中去。
         */
        return Mono.just(mchId).publishOn(Schedulers.parallel()).flatMap(mid -> {
            List<X509Certificate> certificateList = certMapList.get(mchId);
            if (certificateList == null) {
                return updateAndGet(mchId);
            } else {
                X509Certificate certificate = getValidCertificate(certificateList);
                if (certificate == null) {
                    return updateAndGet(mchId);
                }
                return Mono.just(certificate);
            }
        });
    }

    private Mono<X509Certificate> updateAndGet(String mchId) {
        return updateCertificates(mchId).map(list -> {
            this.certMapList.put(mchId, list);
            X509Certificate certificate = getValidCertificate(list);
            if (certificate != null) {
                return certificate;
            } else {
                throw new WxPayException(WxErrorCode.NOT_FOUND_RESOURCE, "cannot get certificates");
            }
        });
    }

    /**
     * 更新证书
     * @param mchId
     */
    private Mono<List<X509Certificate>> updateCertificates(String mchId) {
        return this.certificatesDownloader.getCertificates(mchId).flatMap(certificates-> {
            return this.privateKeyManager.getApiV3Key(mchId).map(apiV3Key -> {
                CertificatesDecoder decoder = new CertificatesDecoder(apiV3Key, certificates);
                List<X509Certificate> list = decoder.decodeToX509List();
                return list;
            });
        });
    }

    /**
     * 在列表中获取有效的证书， 如果无有效证书，则返回null。
     * @param certificateList
     * @return
     */
    private X509Certificate getValidCertificate(List<X509Certificate> certificateList) {
        if (certificateList != null && certificateList.size() > 0) {
            for (X509Certificate x509Certificate : certificateList) {
                try {
                    x509Certificate.checkValidity();
                    return x509Certificate;
                } catch (CertificateExpiredException e) {
                    e.printStackTrace();
                } catch (CertificateNotYetValidException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
