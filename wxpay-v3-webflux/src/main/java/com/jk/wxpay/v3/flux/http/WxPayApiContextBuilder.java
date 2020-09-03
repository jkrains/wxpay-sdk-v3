package com.jk.wxpay.v3.flux.http;

import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxErrorException;
import com.jk.wxpay.v3.flux.http.filter.WxPayExchangeFilter;
import com.jk.wxpay.v3.reactor.request.ApiContext;
import com.jk.wxpay.v3.reactor.service.MerchantPrivateKeyManager;
import com.jk.wxpay.v3.reactor.service.WxCertificatesManager;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 使用这个类来构建 Http Api context.
 * 构造一个缺省的对象， SDK 其他模块，均可以使用该来进行构造。
 *
 */
public class WxPayApiContextBuilder {

    private String baseUrl = "https://api.mch.weixin.qq.com";
    private List<ExchangeFilterFunction> filterFunctions;
    private MerchantPrivateKeyManager merchantPrivateKeyService;
    private WxCertificatesManager wxCertificatesService;

    public WxPayApiContextBuilder() {
        filterFunctions = new ArrayList<>();
    }

    public WxPayApiContextBuilder setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public WxPayApiContextBuilder addFilterFunction(ExchangeFilterFunction filterFunction) {
        this.filterFunctions.add(filterFunction);
        return this;
    }

    public void setMerchantPrivateKeyService(MerchantPrivateKeyManager merchantPrivateKeyService) {
        this.merchantPrivateKeyService = merchantPrivateKeyService;
    }

    public void setWxCertificatesService(WxCertificatesManager wxCertificatesService) {
        this.wxCertificatesService = wxCertificatesService;
    }

    /**
     *
     * 构建一个 ApiContext.
     * @return
     */
    public ApiContext build() {
        if (this.baseUrl == null || "".equals(this.baseUrl)) {
            throw new WxErrorException(WxErrorCode.ILLEGAL_ARG, "BaseUrl is null or empty.");
        }

        if (merchantPrivateKeyService == null) {
            throw new WxErrorException(WxErrorCode.ILLEGAL_ARG, "Merchant private key service is null");
        }

        if (wxCertificatesService == null) {
            throw new WxErrorException(WxErrorCode.ILLEGAL_ARG, "wx certificates service is null");
        }

        WxPayExchangeFilter authTokenExchangeFilter = new WxPayExchangeFilter(merchantPrivateKeyService, wxCertificatesService);
        WebClient webClient = WebClient.builder()
                .filters(c -> c.addAll(this.filterFunctions))
                .baseUrl(this.baseUrl).filter(authTokenExchangeFilter).build();
        HttpRequestClient httpRequestClient = new HttpRequestClient(webClient);
        HttpApiContext httpApiContext = new HttpApiContext(httpRequestClient);
        return httpApiContext;
    }
}
