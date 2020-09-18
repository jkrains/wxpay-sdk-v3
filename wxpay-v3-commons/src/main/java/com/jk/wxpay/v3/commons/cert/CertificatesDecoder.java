package com.jk.wxpay.v3.commons.cert;

import com.jk.wxpay.v3.commons.bean.cert.DecryptCertificateDescription;
import com.jk.wxpay.v3.commons.bean.cert.DecryptCertificateEntity;
import com.jk.wxpay.v3.commons.bean.cert.EncryptCertificate;
import com.jk.wxpay.v3.commons.bean.cert.EncryptCertificateEntity;
import com.jk.wxpay.v3.commons.exception.WxPayException;
import com.jk.wxpay.v3.commons.util.AesUtil;
import com.jk.wxpay.v3.commons.util.PemUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 使用apiV3Key解密证书编程非机密内容。
 */
public class CertificatesDecoder {

    private String apiV3Key;
    private EncryptCertificateEntity encryptEntity;

    public CertificatesDecoder(String apiV3Key, EncryptCertificateEntity encryptEntity) {

        Objects.requireNonNull(apiV3Key);
        Objects.requireNonNull(encryptEntity);

        this.apiV3Key = apiV3Key;
        this.encryptEntity = encryptEntity;
    }

    /**
     * 转换成解密后的实体类。
     * @return
     * @throws WxPayException
     */
    public DecryptCertificateEntity decodeToEntity() throws WxPayException {
        List<DecryptCertificateDescription> decryptList = new ArrayList<>();
        this.encryptEntity.getData().forEach(ed -> {
            try {
                DecryptCertificateDescription decryptItem = new DecryptCertificateDescription();
                EncryptCertificate certificate = ed.getEncryptCertificate();
                decryptItem.setSerialNo(ed.getSerialNo())
                        .setEffectiveTime(ed.getEffectiveTime())
                        .setExpireTime(ed.getExpireTime())
                        .setCertText(AesUtil.decryptToString(apiV3Key.getBytes(),
                                certificate.getAssociatedData().getBytes(),
                                certificate.getNonce().getBytes(), certificate.getCipherText()));
                decryptList.add(decryptItem);
            } catch (GeneralSecurityException e) {
                throw new WxPayException(GeneralSecurityException.class.getSimpleName(),e.getMessage());
            }
        });
        return new DecryptCertificateEntity(decryptList);
    }

    /**
     * 转换成java证书列表, 如果有无效的数据，该函数会抛出异常。
     * @return
     */
    public List<X509Certificate> decodeToX509List() throws WxPayException {
        List<X509Certificate> decryptList = new ArrayList<>();
        this.encryptEntity.getData().forEach(ed -> {
            try {
                EncryptCertificate certificate = ed.getEncryptCertificate();
                String decryptText = AesUtil.decryptToString(apiV3Key.getBytes(),
                        certificate.getAssociatedData().getBytes(),
                        certificate.getNonce().getBytes(), certificate.getCipherText());
                ByteArrayInputStream inputStream = new ByteArrayInputStream(decryptText.getBytes(StandardCharsets.UTF_8));
                X509Certificate x509Cert = PemUtils.loadCertificate(inputStream);
                decryptList.add(x509Cert);
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            } catch (GeneralSecurityException e) {
                throw new WxPayException(GeneralSecurityException.class.getSimpleName(),e.getMessage());
            }
        });
        return decryptList;
    }
}
