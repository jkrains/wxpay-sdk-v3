package com.jk.wxpay.v3.commons.exception;

/**
 * 定义了一些Http常见的状态码。
 */
public interface StatusCode {
    int ST_OK = 200;  //处理成功
    int ST_NO_CONTENT = 204;  //处理成功，无返回Body
    int ST_BAD_REQUEST = 400; //协议或者参数非法  请根据接口返回的详细信息检查您的程序
    int ST_UNAUTHORIZED = 401; //签名验证失败 请检查签名参数和方法是否都符合签名算法要求
    int ST_FORBIDDEN = 403; //权限异常  请开通商户号相关权限。请联系产品或商务申请
    int ST_NOT_FOUND  = 404; // 请求的资源不存在 请商户检查需要查询的id或者请求URL是否正确
    int ST_TOO_MANY = 429;   // 请求超过频率限制 请求未受理，请降低频率后重试
    int ST_SERVER_ERROR = 500; // 系统错误 按具体接口的错误指引进行重试
    int ST_BAD_GATEWAY = 502;  // 服务下线，暂时不可用 请求无法处理，请稍后重试
    int ST_SERVICE_UNAVAILABLE = 503;  // 服务不可用，过载保护 请求无法处理，请稍后重试
}
