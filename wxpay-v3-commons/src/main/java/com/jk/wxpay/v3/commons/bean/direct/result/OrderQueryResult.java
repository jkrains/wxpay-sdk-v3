package com.jk.wxpay.v3.commons.bean.direct.result;

import com.google.gson.annotations.SerializedName;
import com.jk.wxpay.v3.commons.bean.direct.*;
import com.jk.wxpay.v3.commons.bean.direct.query.QueryOrderAmount;

import java.util.List;

/**
 * 订单查询结果
 */
public class OrderQueryResult {

    /**
     * TradeType的枚举定义
     */
    public enum TradeType {

        JSAPI("JSAPI"),
        NATIVE("NATIVE"),
        APP("APP"),
        MICROPAY("MICROPAY"),
        MWEB("MWEB"),
        FACEPAY("FACEPAY");

        private String name;
        TradeType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * TradeState的枚举定义
     */
    public enum TradeState {
        SUCCESS("SUCCESS"),
        REFUND("REFUND"),
        NOTPAY("NOTPAY"),
        CLOSED("CLOSED"),
        REVOKED("REVOKED"),
        USERPAYING("USERPAYING"),
        PAYERROR("PAYERROR");

        TradeState(String name) {
            this.name = name;
        }
        private String name;

        public String getName() {
            return name;
        }
    }
    /**
     * 直连商户申请的公众号或移动应用appid。
     * 示例值：wxd678efh567hg6787
     * [r]
     */
    @SerializedName("appid")
    private String appId;

    /**
     * 直连商户的商户号，由微信支付生成并下发。
     * 示例值：1230000109
     * [r]
     */
    @SerializedName("mchid")
    private String mchId;

    /**
     * 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一，详见【商户订单号】。
     * 示例值：1217752501201407033233368018
     * [r]
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * 微信支付系统生成的订单号。
     * 示例值：1217752501201407033233368018
     * [o]
     */
    @SerializedName("transaction_id")
    private String transactionId;

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
     * 交易状态描述
     * 示例值：支付失败，请重新下单支付
     * [r]
     */
    @SerializedName("trade_state_desc")
    private String tradeStateDesc;

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
     * 支付者信息, 当这个数据结构被映射成 回调通知结果时， 选用alternate 指向的名称
     * [r]
     */
    @SerializedName(value = "player", alternate = "combine_payer_info")
    private PayPlayer player;

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

    /**
     * 优惠功能，享受优惠时返回该字段。
     * [o]
     */
    @SerializedName("promotion_detail")
    private List<PromotionDetail> promotionDetail;

    public OrderQueryResult() {
    }

    public String getAppId() {
        return appId;
    }

    public OrderQueryResult setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getMchId() {
        return mchId;
    }

    public OrderQueryResult setMchId(String mchId) {
        this.mchId = mchId;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public OrderQueryResult setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public OrderQueryResult setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getTradeType() {
        return tradeType;
    }

    public OrderQueryResult setTradeType(String tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public String getTradeState() {
        return tradeState;
    }

    public OrderQueryResult setTradeState(String tradeState) {
        this.tradeState = tradeState;
        return this;
    }

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public OrderQueryResult setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
        return this;
    }

    public String getBankType() {
        return bankType;
    }

    public OrderQueryResult setBankType(String bankType) {
        this.bankType = bankType;
        return this;
    }

    public String getAttach() {
        return attach;
    }

    public OrderQueryResult setAttach(String attach) {
        this.attach = attach;
        return this;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public OrderQueryResult setSuccessTime(String successTime) {
        this.successTime = successTime;
        return this;
    }

    public PayPlayer getPlayer() {
        return player;
    }

    public OrderQueryResult setPlayer(PayPlayer player) {
        this.player = player;
        return this;
    }

    public QueryOrderAmount getAmount() {
        return amount;
    }

    public OrderQueryResult setAmount(QueryOrderAmount amount) {
        this.amount = amount;
        return this;
    }

    public SceneInfo getSceneInfo() {
        return sceneInfo;
    }

    public OrderQueryResult setSceneInfo(SceneInfo sceneInfo) {
        this.sceneInfo = sceneInfo;
        return this;
    }

    public List<PromotionDetail> getPromotionDetail() {
        return promotionDetail;
    }

    public OrderQueryResult setPromotionDetail(List<PromotionDetail> promotionDetail) {
        this.promotionDetail = promotionDetail;
        return this;
    }
}
