package com.jk.wxpay.v3.apache.http;

import com.jk.wxpay.v3.block.MerchantPrivateKeyManager;
import com.jk.wxpay.v3.block.WxCertificatesManager;
import com.jk.wxpay.v3.block.request.ApiContext;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxErrorException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiContextBuilder {
    private static final Logger log = LoggerFactory.getLogger(ApiContextBuilder.class.getSimpleName());
    private String hostUrl = Constants.PAY_HOST_URL;
    private MerchantPrivateKeyManager merchantPrivateKeyManager;
    private WxCertificatesManager wxCertificatesManager;

    public ApiContextBuilder() {
    }

    public ApiContextBuilder setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
        return this;
    }

    public ApiContextBuilder setMerchantPrivateKeyManager(MerchantPrivateKeyManager merchantPrivateKeyManager) {
        this.merchantPrivateKeyManager = merchantPrivateKeyManager;
        return this;
    }

    public ApiContextBuilder setWxCertificatesManager(WxCertificatesManager wxCertificatesManager) {
        this.wxCertificatesManager = wxCertificatesManager;
        return this;
    }

    /**
     *
     * 构建一个 ApiContext, 使用这个ApiContext就可以调用模块提供的API.
     * @return
     */
    public ApiContext build() {
        if (this.hostUrl == null || "".equals(this.hostUrl)) {
            throw new WxErrorException(WxErrorCode.ILLEGAL_ARG, "BaseUrl is null or empty.");
        }

        if (merchantPrivateKeyManager == null) {
            throw new WxErrorException(WxErrorCode.ILLEGAL_ARG, "Merchant private key manager is null");
        }

        if (wxCertificatesManager == null) {
            log.warn("Wx certificates manager is invalid, do not verify the response info");
        }

        CloseableHttpClientBuilder builder = new CloseableHttpClientBuilder();
        CloseableHttpClient httpClient = builder.setMerchantPrivateKeyManager(this.merchantPrivateKeyManager)
                .setWxCertificatesManager(this.wxCertificatesManager)
                .build();

        HttpRequestClient httpRequestClient = new HttpRequestClient(hostUrl, httpClient);
        HttpApiContext httpApiContext = new HttpApiContext(httpRequestClient);
        return httpApiContext;
    }
}
