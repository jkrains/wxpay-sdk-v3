package com.jk.wxpay.v3.block.request;

import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxErrorException;
import com.jk.wxpay.v3.commons.util.JsonUtils;

import java.util.Map;

/**
 * Http请求的一个简单封装。 可以继承这个类完成一些操作。
 * 传递请求的body类型 和返回的类型。
 */
public class SingleRequester<T, R> {

    private final ApiContext apiContext;
    private final String path;
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
        this.apiContext = apiContext;
        this.path = path;
        this.classT = classT;
        this.classR = classR;
    }

    public ApiContext getApiContext() {
        return apiContext;
    }

    public String getPath() {
        return path;
    }

    public Class<T> getClassT() {
        return classT;
    }

    public Class<R> getClassR() {
        return classR;
    }

    public String requestString(
            RequestMethod method,
            String subPath,
            Map<String, Object> params,
            Map<String, String> headers,
            String body) {
        if (this.apiContext != null && this.apiContext.available()) {
            Object r = this.apiContext.getRequestClient().request(method, subPath, params, headers, body);
            if (r instanceof String) {
                return String.class.cast(r);
            } else {
                throw  new WxErrorException(WxErrorCode.NOT_SUPPORTED_TYPE, "returns not supported");
            }
        } else {
            throw new WxErrorException(WxErrorCode.ILLEGAL_ARG, "apiContext is invalid");
        }
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

        String finalPath = this.path;
        if (subPath != null) {
            finalPath = this.path + subPath;
        }

        String jstr = this.requestString(method, finalPath, params, headers, bodyStr);
        return JsonUtils.fromJson(jstr, this.classR);
    }

    public R request(RequestMethod method, String subPath, Map<String, Object> params, T body) {
        return this.requestWithHeader(method, subPath, params,null, body);
    }

    public R post(String subPath, Map<String, Object> params, T body) {
        return this.request(RequestMethod.POST, subPath, params, body);
    }

    public R post(Map<String, Object> params, T body) {
        return this.post(null, params, body);
    }

    public R post(T body) {
        return this.post(null, body);
    }

    public R get(String subPath, Map<String, Object> params) {
        return this.request(RequestMethod.GET, subPath, params, null);
    }

    public R get(Map<String, Object> params) {
        return this.request(RequestMethod.GET, null, params, null);
    }

    public R get() {
        return this.get(null, null);
    }
}
