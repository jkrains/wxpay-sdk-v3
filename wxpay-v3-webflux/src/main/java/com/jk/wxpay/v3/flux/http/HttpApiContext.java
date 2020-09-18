package com.jk.wxpay.v3.flux.http;


import com.jk.sdk.commons.reactor.ApiContext;
import com.jk.sdk.commons.reactor.RequestClient;

/**
 * Http Api Context.
 */
public class HttpApiContext implements ApiContext {

    private final HttpRequestClient httpRequestClient;

    public HttpApiContext(HttpRequestClient httpRequestClient) {
        this.httpRequestClient = httpRequestClient;
    }

    @Override
    public RequestClient getRequestClient() {
        return this.httpRequestClient;
    }

    @Override
    public boolean available() {
        if (this.httpRequestClient != null) {
            return this.httpRequestClient.available();
        }
        return false;
    }
}
