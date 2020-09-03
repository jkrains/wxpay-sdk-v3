package com.jk.wxpay.v3.block.request;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义了一些请求的方法。
 */
public enum RequestMethod {

    /**
     *  fire_forget  rsocket 的特性，发送出去不管结果， 这个不需要支持。
     *  request_stream
     *
     *  get stream
     */
    GET, GET_STREAM, POST, PUT, PATCH, DELETE;

    private static final Map<String, RequestMethod> mappings = new HashMap<>(16);

    static {
        for (RequestMethod httpMethod : values()) {
            mappings.put(httpMethod.name(), httpMethod);
        }
    }

    /**
     * Resolve the given method value to an {@code HttpMethod}.
     * @param method the method value as a String
     * @return the corresponding {@code HttpMethod}, or {@code null} if not found
     * @since 4.2.4
     */

    public static RequestMethod resolve(String method) {
        return (method != null ? mappings.get(method) : null);
    }

    /**
     * Determine whether this {@code HttpMethod} matches the given
     * method value.
     * @param method the method value as a String
     * @return {@code true} if it matches, {@code false} otherwise
     * @since 4.2.4
     */
    public boolean matches(String method) {
        return (this == resolve(method));
    }
}
