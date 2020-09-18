package com.jk.wxpay.v3.block.api;

import com.jk.sdk.commons.block.ApiContext;
import com.jk.sdk.commons.block.RequestMethod;
import com.jk.sdk.commons.block.StringRequester;
import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxPayException;
import com.jk.wxpay.v3.commons.util.JsonUtils;

import java.util.Map;

/**
 * Http请求的一个简单封装。 可以继承这个类完成一些操作。
 * 传递请求的body类型 和返回的类型。
 */
public class SingleRequester<T, R> extends StringRequester {

    private final Class<T> classT;
    private final Class<R> classR;

    /**
     * 构造方法。
     * @param apiContext
     * @param path
     * @param classT
     * @param classR
     */
    public SingleRequester(ApiContext apiContext, String path, Class<T> classT, Class<R> classR) {
        super(apiContext, path);
        this.classT = classT;
        this.classR = classR;
    }

    public Class<T> getClassT() {
        return classT;
    }

    public Class<R> getClassR() {
        return classR;
    }

    public R requestWithHeader(RequestMethod method,
                    String subPath,
                    Map<String, Object> params,
                    Map<String, String> headers,
                    T body) {
        String bodyStr = null;
        if (body != null) {
            bodyStr = JsonUtils.toJson(body);
        }

        String jstr = this.requestString(method, subPath, params, headers, bodyStr);
        return JsonUtils.fromJson(jstr, this.classR);
    }

    public R request(RequestMethod method, String subPath, Map<String, Object> params, Map<String, String> headers, T body) {
        return this.requestWithHeader(method, subPath, params,headers, body);
    }

    public R post(String subPath, Map<String, Object> params, Map<String, String> headers, T body) {
        return this.request(RequestMethod.POST, subPath, params, headers, body);
    }

    public R post(Map<String, Object> params, Map<String, String> headers, T body) {
        return this.post(null, params, headers, body);
    }

    public R post(T body) {
        return this.post(null, null, body);
    }

    public R get(String subPath, Map<String, Object> params, Map<String, String> headers) {
        return this.request(RequestMethod.GET, subPath, params, headers, null);
    }

    public R get(Map<String, Object> params, Map<String, String> headers) {
        return this.request(RequestMethod.GET, null, params, headers, null);
    }

    public R get() {
        return this.get(null, null);
    }
}
