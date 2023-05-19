package com.jk.wxpay.v3.commons.bean.combine;

/**
 * 合单关闭，子单信息
 */
public class CloseSubOrder {
    /**
     * 子单发起方商户号，必须与发起方appid有绑定关系。
     * 示例值：1900000109
     * [r]
     */
    private String mchId;

    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * 示例值：20150806125346
     * [r]
     */
    private String outTradeNo;

    /**
     * 二级商户商户号，由微信支付生成并下发。
     * 注意：仅适用于电商平台 服务商
     * 示例值：1900000109
     * [r]
     */
    private String subMchId;

    public CloseSubOrder() {
    }

    public String getMchId() {
        return mchId;
    }

    public CloseSubOrder setMchId(String mchId) {
        this.mchId = mchId;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public CloseSubOrder setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public CloseSubOrder setSubMchId(String subMchId) {
        this.subMchId = subMchId;
        return this;
    }
}
