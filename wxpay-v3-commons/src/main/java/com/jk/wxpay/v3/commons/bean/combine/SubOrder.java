package com.jk.wxpay.v3.commons.bean.combine;

import com.google.gson.annotations.SerializedName;
import com.jk.wxpay.v3.commons.bean.direct.OrderAmount;

public class SubOrder {
    /**
     * 子单发起方商户号，必须与发起方appid有绑定关系。
     * 示例值：1900000109
     * [r]
     */
    @SerializedName("mchid")
    private String mchId;

    /**
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     * 示例值：深圳分店
     * [r]
     */
    private String attach;

    /**
     * 订单金额信息
     * [r]
     */
    private OrderAmount amount;

    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * 示例值：20150806125346
     * [r]
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * 二级商户商户号，由微信支付生成并下发。
     * 注意：仅适用于电商平台 服务商
     * 示例值：1900000109
     * [r]
     */
    @SerializedName("sub_mchid")
    private String subMchId;

    /**
     *
     * [r]
     */
    private String description;

    /**
     * 结算信息
     * [o]
     */
    @SerializedName("settle_info")
    private SettleInfo settleInfo;

    public SubOrder() {
    }

    public String getMchId() {
        return mchId;
    }

    public SubOrder setMchId(String mchId) {
        this.mchId = mchId;
        return this;
    }

    public String getAttach() {
        return attach;
    }

    public SubOrder setAttach(String attach) {
        this.attach = attach;
        return this;
    }

    public OrderAmount getAmount() {
        return amount;
    }

    public SubOrder setAmount(OrderAmount amount) {
        this.amount = amount;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public SubOrder setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public SubOrder setSubMchId(String subMchId) {
        this.subMchId = subMchId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SubOrder setDescription(String description) {
        this.description = description;
        return this;
    }

    public SettleInfo getSettleInfo() {
        return settleInfo;
    }

    public SubOrder setSettleInfo(SettleInfo settleInfo) {
        this.settleInfo = settleInfo;
        return this;
    }
}
