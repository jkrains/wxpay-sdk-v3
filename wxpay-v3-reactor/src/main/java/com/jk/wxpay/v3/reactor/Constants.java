package com.jk.wxpay.v3.reactor;

/**
 * 定义了一些常用的常量
 */
public interface Constants {
    /**
     * 微信支付基本域名后缀
     */
    String WX_BASE_URL_SUFFIX = ".mch.weixin.qq.com";
    /**
     * 即刻易用自定义 header, 用于应用与拦截器之间传递信息。
     */
    String JK_MCH_ID = "jk_mchId";
}
