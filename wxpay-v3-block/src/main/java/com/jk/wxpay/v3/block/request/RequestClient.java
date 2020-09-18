package com.jk.wxpay.v3.block.request;



import com.jk.wxpay.v3.commons.exception.WxPayException;

import java.util.Map;

/**
 * 定义一个 RequestClient接口
 */
public interface RequestClient {

    /**
     *
     * @param method 请求方法
     * @param subPath 子路径
     * @param params 参数 url 后面赋值，如 http://xxx?limit=2&count=100
     * @param headers 用户自定义 http头内容。
     * @param body 内容，目前只支持string类型。
     * @return 返回Object类型，目前只支持 String 类型， 返回成功的结果，请求失败时，通过error接口拿到值。 其他类型后续支持。。
     */
    Object request(
            RequestMethod method,
            String subPath,
            Map<String, Object> params,
            Map<String, String> headers,
            Object body) throws WxPayException;

    boolean available();
}
