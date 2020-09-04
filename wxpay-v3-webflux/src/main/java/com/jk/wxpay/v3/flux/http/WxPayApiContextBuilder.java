package com.jk.wxpay.v3.flux.http;

import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxErrorException;
import com.jk.wxpay.v3.flux.http.filter.WxPayExchangeFilter;
import com.jk.wxpay.v3.reactor.request.ApiContext;
import com.jk.wxpay.v3.reactor.MerchantPrivateKeyManager;
import com.jk.wxpay.v3.reactor.WxCertificatesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 使用这个类来构建 Http Api context.
 * 构造一个缺省的对象， SDK 其他模块，均可以使用该来进行构造。
 * 注意： 如果不设置WxCertificatesManager则，对微信的结果不进行验签， 这种情况下主要用于下载微信证书。
 */
public class WxPayApiContextBuilder {
    private static final Logger log = LoggerFactory.getLogger(WxPayApiContextBuilder.class.getSimpleName());
    private String hostUrl = Constants.PAY_HOST_URL;
    private MerchantPrivateKeyManager merchantPrivateKeyManager;
    private WxCertificatesManager wxCertificatesManager;

    private List<ExchangeFilterFunction> filterFunctions;


    public WxPayApiContextBuilder() {
        filterFunctions = new ArrayList<>();
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public WxPayApiContextBuilder addFilterFunction(ExchangeFilterFunction filterFunction) {
        this.filterFunctions.add(filterFunction);
        return this;
    }

    public void setMerchantPrivateKeyManager(MerchantPrivateKeyManager merchantPrivateKeyManager) {
        this.merchantPrivateKeyManager = merchantPrivateKeyManager;
    }

    public void setWxCertificatesManager(WxCertificatesManager wxCertificatesManager) {
        this.wxCertificatesManager = wxCertificatesManager;
    }

    /**
     *
     * 构建一个 ApiContext.
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
            log.warn("wx certificates manager is invalid, the response will not be verified");
        }

        WxPayExchangeFilter authTokenExchangeFilter = new WxPayExchangeFilter(merchantPrivateKeyManager, wxCertificatesManager);
        WebClient webClient = WebClient.builder()
                .filters(c -> c.addAll(this.filterFunctions))
                .baseUrl(this.hostUrl).filter(authTokenExchangeFilter).build();
        HttpRequestClient httpRequestClient = new HttpRequestClient(webClient);
        HttpApiContext httpApiContext = new HttpApiContext(httpRequestClient);
        return httpApiContext;
    }
}
