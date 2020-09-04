package com.jk.wxpay.v3.commons.bean.cert;

import java.util.List;

/**
 * 下载的证书实体
 */
public class EncryptCertificateEntity {

    private List<EncryptCertificateDescription> data;

    public EncryptCertificateEntity() {
    }

    public EncryptCertificateEntity(List<EncryptCertificateDescription> data) {
        this.data = data;
    }

    public List<EncryptCertificateDescription> getData() {
        return data;
    }

    public EncryptCertificateEntity setData(List<EncryptCertificateDescription> data) {
        this.data = data;
        return this;
    }
}
