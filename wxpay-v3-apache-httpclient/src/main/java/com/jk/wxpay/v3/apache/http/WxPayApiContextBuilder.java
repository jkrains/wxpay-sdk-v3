package com.jk.wxpay.v3.apache.http;

import com.jk.wxpay.v3.apache.http.filter.WxPayExchangeFilter;
import com.jk.wxpay.v3.block.MerchantPrivateKeyManager;
import com.jk.wxpay.v3.block.WxCertificatesManager;
import com.jk.wxpay.v3.block.request.ApiContext;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxErrorException;
import org.apache.http.impl.client.CloseableHttpClient;

public class WxPayApiContextBuilder {

    private String hostUrl = Constants.PAY_HOST_URL;
    private MerchantPrivateKeyManager merchantPrivateKeyManager;
    private WxCertificatesManager wxCertificatesManager;

    public WxPayApiContextBuilder() {
    }

    public WxPayApiContextBuilder setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
        return this;
    }

    public WxPayApiContextBuilder setMerchantPrivateKeyManager(MerchantPrivateKeyManager merchantPrivateKeyManager) {
        this.merchantPrivateKeyManager = merchantPrivateKeyManager;
        return this;
    }

    public WxPayApiContextBuilder setWxCertificatesManager(WxCertificatesManager wxCertificatesManager) {
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
            throw new WxErrorException(WxErrorCode.ILLEGAL_ARG, "Wx certificates manager is null");
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
