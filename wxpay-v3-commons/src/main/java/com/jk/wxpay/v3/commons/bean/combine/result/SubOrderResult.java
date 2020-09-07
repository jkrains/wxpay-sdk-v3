package com.jk.wxpay.v3.commons.bean.combine.result;

import com.google.gson.annotations.SerializedName;
import com.jk.wxpay.v3.commons.bean.direct.PayPlayer;
import com.jk.wxpay.v3.commons.bean.direct.SceneInfo;
import com.jk.wxpay.v3.commons.bean.direct.query.QueryOrderAmount;

/**
 * sub order result.
 */
public class SubOrderResult {
    /**
     * 直连商户的商户号，由微信支付生成并下发。
     * 示例值：1230000109
     * [r]
     */
    @SerializedName("mchid")
    private String mchId;



    /**
     * 交易类型，枚举值：
     * JSAPI：公众号支付
     * NATIVE：扫码支付
     * APP：APP支付
     * MICROPAY：付款码支付
     * MWEB：H5支付
     * FACEPAY：刷脸支付
     * 示例值：MICROPAY
     * [r]
     */
    @SerializedName("trade_type")
    private String tradeType;

    /**
     * 交易状态，枚举值：
     * SUCCESS：支付成功
     * REFUND：转入退款
     * NOTPAY：未支付
     * CLOSED：已关闭
     * REVOKED：已撤销（付款码支付）
     * USERPAYING：用户支付中（付款码支付）
     * PAYERROR：支付失败(其他原因，如银行返回失败)
     * 示例值：SUCCESS
     * [r]
     */
    @SerializedName("trade_state")
    private String tradeState;


    /**
     * 银行类型，采用字符串类型的银行标识。
     * 示例值：CMC
     * [o]
     */
    @SerializedName("bank_type")
    private String bankType;

    /**
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
     * 示例值：自定义数据
     * [o]
     */
    private String attach;

    /**
     * 支付完成时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，
     * T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，
     * TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
     * 例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
     * 示例值：2018-06-08T10:34:56+08:00
     * [o]
     */
    @SerializedName("success_time")
    private String successTime;

    /**
     * 微信支付订单号。
     * 示例值：1009660380201506130728806387
     */
    @SerializedName("transaction_id")
    private String transactionId;

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
     * 订单金额信息，当支付成功时返回该字段
     * [o]
     */
    private QueryOrderAmount amount;

    /**
     * 支付场景描述
     * [o]
     */
    @SerializedName("scene_info")
    private SceneInfo sceneInfo;

    public SubOrderResult() {
    }

    public String getMchId() {
        return mchId;
    }

    public SubOrderResult setMchId(String mchId) {
        this.mchId = mchId;
        return this;
    }

    public String getTradeType() {
        return tradeType;
    }

    public SubOrderResult setTradeType(String tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public String getTradeState() {
        return tradeState;
    }

    public SubOrderResult setTradeState(String tradeState) {
        this.tradeState = tradeState;
        return this;
    }

    public String getBankType() {
        return bankType;
    }

    public SubOrderResult setBankType(String bankType) {
        this.bankType = bankType;
        return this;
    }

    public String getAttach() {
        return attach;
    }

    public SubOrderResult setAttach(String attach) {
        this.attach = attach;
        return this;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public SubOrderResult setSuccessTime(String successTime) {
        this.successTime = successTime;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public SubOrderResult setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public SubOrderResult setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public SubOrderResult setSubMchId(String subMchId) {
        this.subMchId = subMchId;
        return this;
    }

    public QueryOrderAmount getAmount() {
        return amount;
    }

    public SubOrderResult setAmount(QueryOrderAmount amount) {
        this.amount = amount;
        return this;
    }

    public SceneInfo getSceneInfo() {
        return sceneInfo;
    }

    public SubOrderResult setSceneInfo(SceneInfo sceneInfo) {
        this.sceneInfo = sceneInfo;
        return this;
    }
}
