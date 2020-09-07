package com.jk.wxpay.v3.commons.bean.combine;

import com.google.gson.annotations.SerializedName;

/**
 * 合单关闭参数
 */
public class SubOrderCloseInfo {

    /**
     * body 合单发起方的appId。
     * 示例值：wxd678efh567hg6787
     * [r]
     */
    @SerializedName("combine_appid")
    private String combineAppId;

    /**
     * 合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * 示例值：P20150806125346
     * [r]
     */
    @SerializedName("combine_out_trade_no")
    private String combineOutTradeNo;

    /**
     * 最多支持子单条数：50
     * [r]
     */
    @SerializedName("sub_orders")
    private CloseSubOrder subOrder;

    public SubOrderCloseInfo() {
    }

    public String getCombineAppId() {
        return combineAppId;
    }

    public SubOrderCloseInfo setCombineAppId(String combineAppId) {
        this.combineAppId = combineAppId;
        return this;
    }

    public String getCombineOutTradeNo() {
        return combineOutTradeNo;
    }

    public SubOrderCloseInfo setCombineOutTradeNo(String combineOutTradeNo) {
        this.combineOutTradeNo = combineOutTradeNo;
        return this;
    }

    public CloseSubOrder getSubOrder() {
        return subOrder;
    }

    public SubOrderCloseInfo setSubOrder(CloseSubOrder subOrder) {
        this.subOrder = subOrder;
        return this;
    }
}
