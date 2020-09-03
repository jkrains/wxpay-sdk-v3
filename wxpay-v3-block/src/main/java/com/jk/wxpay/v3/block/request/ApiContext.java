package com.jk.wxpay.v3.block.request;

/**
 * client context 接口。
 */
public interface ApiContext {
    /**
     * 获取当前 request client.
     * @return
     */
    RequestClient getRequestClient();

    /**
     * 当前上下文是否有效。
     * @return
     */
    boolean available();
}
