package com.jk.wxpay.v3.apache.http;

import com.jk.wxpay.v3.block.request.ApiContext;
import com.jk.wxpay.v3.block.request.RequestClient;

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
