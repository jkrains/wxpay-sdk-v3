package com.jk.wxpay.v3.apache;

import com.jk.wxpay.v3.apache.http.WxPayApiContextBuilder;
import com.jk.wxpay.v3.block.MerchantPrivateKeyManager;
import com.jk.wxpay.v3.block.WxCertificatesManager;
import com.jk.wxpay.v3.block.api.CertificatesDownloader;
import com.jk.wxpay.v3.block.request.ApiContext;
import com.jk.wxpay.v3.commons.bean.cert.EncryptCertificateEntity;
import com.jk.wxpay.v3.commons.cert.CertificatesDecoder;
import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxErrorException;

import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这个函数完成自动从微信服务器上进行更新。 来下载微信证书。证书保存在当前模块的内存中。
 * 本模块依赖于MerchantPrivateKeyManager来进行进行
 */
public class WxMemoryCacheCertificatesManager implements WxCertificatesManager {

    private final CertificatesDownloader certificatesDownloader;
    private final MerchantPrivateKeyManager privateKeyManager;
    /**
     * 用map来存储密钥。
     */
    private final Map<String, List<X509Certificate>> certMapList = Collections.synchronizedMap(new HashMap<>());

    public WxMemoryCacheCertificatesManager(MerchantPrivateKeyManager privateKeyManager) {
        this.privateKeyManager = privateKeyManager;
        ApiContext apiContext = new WxPayApiContextBuilder().setMerchantPrivateKeyManager(privateKeyManager).build();
        this.certificatesDownloader = new CertificatesDownloader(apiContext);
    }

    /**
     *
     * 获取有效的wx端证书。
     * @param mchId
     * @return
     */
    @Override
    public X509Certificate getValidCertificate(String mchId) {
        X509Certificate validCertificate;
        List<X509Certificate> certificateList = certMapList.get(mchId);
        if (certificateList != null) {
            validCertificate = getValidCertificate(certificateList);
            if (validCertificate != null) {
                return validCertificate;
            } else {
                // update 证书。
                updateCertificates(mchId);
            }
        } else {
            // update 证书
            updateCertificates(mchId);
        }

        // 再次从列表中获取一次证书。
        certificateList = certMapList.get(mchId);
        validCertificate = getValidCertificate(certificateList);
        if (validCertificate != null) {
            return validCertificate;
        } else {
            throw new WxErrorException(WxErrorCode.NOT_FOUND_RESOURCE, "valid wx certificates not found");
        }
    }

    /**
     * 更新证书
     * @param mchId
     */
    private void updateCertificates(String mchId) {
        EncryptCertificateEntity certificates = this.certificatesDownloader.getCertificates(mchId);
        CertificatesDecoder decoder = new CertificatesDecoder(this.privateKeyManager.getApiV3Key(mchId), certificates);
        List<X509Certificate> list = decoder.decodeToX509List();
        if (list != null) {
            this.certMapList.put(mchId, list);
        }
    }

    /**
     * 在列表中获取有效的证书， 如果无有效证书，则返回null.
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
