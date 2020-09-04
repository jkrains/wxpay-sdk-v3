package com.jk.wxpay.v3.commons.bean.cert;

import java.util.List;

public class DecryptCertificateEntity {

    private List<DecryptCertificateDescription> data;

    public DecryptCertificateEntity() {
    }

    public DecryptCertificateEntity(List<DecryptCertificateDescription> data) {
        this.data = data;
    }

    public List<DecryptCertificateDescription> getData() {
        return data;
    }

    public DecryptCertificateEntity setData(List<DecryptCertificateDescription> data) {
        this.data = data;
        return this;
    }
}
