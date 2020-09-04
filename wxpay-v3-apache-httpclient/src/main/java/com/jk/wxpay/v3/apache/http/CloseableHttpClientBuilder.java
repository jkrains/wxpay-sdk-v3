package com.jk.wxpay.v3.apache.http;
import com.jk.wxpay.v3.apache.http.filter.ExchangeFilter;
import com.jk.wxpay.v3.block.MerchantPrivateKeyManager;
import com.jk.wxpay.v3.block.WxCertificatesManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.execchain.ClientExecChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  使用这个类可以构建HttpClient 进行网络通信。
 *  注意： 构建这个类时，必须传递 MerchantPrivateKeyManager且有效。 当不传递WxCertificatesManager时，不做验签， 这种场合应用于下载微信证书。
*/
public class CloseableHttpClientBuilder extends HttpClientBuilder {
    private static final Logger log = LoggerFactory.getLogger(CloseableHttpClientBuilder.class.getSimpleName());
    private MerchantPrivateKeyManager merchantPrivateKeyManager;
    private WxCertificatesManager wxCertificatesManager;

    public CloseableHttpClientBuilder() {
        String userAgent = HttpUtils.getUserAgent();
        super.setUserAgent(userAgent);
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
            log.warn("Wx certificates manager is invalid, do not verify the response info");
        }
        return super.build();
    }

    @Override
    protected ClientExecChain decorateProtocolExec(final ClientExecChain requestExecutor) {
        return new ExchangeFilter(requestExecutor, this.merchantPrivateKeyManager, this.wxCertificatesManager);
    }
}
