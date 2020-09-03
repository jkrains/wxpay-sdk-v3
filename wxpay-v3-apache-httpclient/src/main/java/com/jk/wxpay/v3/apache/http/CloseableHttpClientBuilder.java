package com.jk.wxpay.v3.apache.http;
import com.jk.wxpay.v3.apache.http.filter.WxPayExchangeFilter;
import com.jk.wxpay.v3.block.MerchantPrivateKeyManager;
import com.jk.wxpay.v3.block.WxCertificatesManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.execchain.ClientExecChain;

/**
 *
 */
public class CloseableHttpClientBuilder extends HttpClientBuilder {

    private MerchantPrivateKeyManager merchantPrivateKeyManager;
    private WxCertificatesManager wxCertificatesManager;

    public CloseableHttpClientBuilder() {
        super.setUserAgent(HttpUtils.getUserAgent());
    }

    public CloseableHttpClientBuilder setMerchantPrivateKeyManager(MerchantPrivateKeyManager merchantPrivateKeyManager) {
        this.merchantPrivateKeyManager = merchantPrivateKeyManager;
        return this;
    }

    public CloseableHttpClientBuilder setWxCertificatesManager(WxCertificatesManager wxCertificatesManager) {
        this.wxCertificatesManager = wxCertificatesManager;
        return this;
    }

    @Override
    public CloseableHttpClient build() {
        if (merchantPrivateKeyManager == null) {
            throw new IllegalArgumentException("Merchant key manager is invalid");
        }
        if (wxCertificatesManager == null) {
            throw new IllegalArgumentException("Wx certificates manager is invalid");
        }
        return super.build();
    }

    @Override
    protected ClientExecChain decorateProtocolExec(final ClientExecChain requestExecutor) {
        return new WxPayExchangeFilter(requestExecutor, this.merchantPrivateKeyManager, this.wxCertificatesManager);
    }
}
