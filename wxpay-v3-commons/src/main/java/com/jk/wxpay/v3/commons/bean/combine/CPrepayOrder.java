package com.jk.wxpay.v3.commons.bean.combine;

import com.google.gson.annotations.SerializedName;
import com.jk.wxpay.v3.commons.bean.direct.PayPlayer;
import com.jk.wxpay.v3.commons.bean.direct.SceneInfo;

/**
 * 使用合单支付接口，用户只输入一次密码，即可完成多个订单的支付。目前最多一次可支持50笔订单进行合单支付。
 * 注意：
 * • 订单如果需要进行抽佣等，需要在合单中指定需要进行分账（profit_sharing为true）；指定后，交易资金进入二级商户账户，处于冻结状态，
 * 可在后续使用分账接口进行分账，利用分账完结进行资金解冻，实现抽佣和对二级商户的账期。
 */
public class CPrepayOrder {

    /**
     * 合单发起方的appid。
     * 示例值：wxd678efh567hg6787
     * [r]
     */
    @SerializedName("combine_appid")
    private String combineAppId;

    /**
     * 合单发起方商户号。
     * 示例值：1900000109
     * [r]
     */
    @SerializedName("combine_mchid")
    private String combineMchId;

    /**
     * 合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一 。
     * 示例值：P20150806125346
     * [r]
     */
    @SerializedName("combine_out_trade_no")
    private String combineOutTradeNo;

    /**
     * 支付场景信息描述
     * [o]
     */
    @SerializedName("scene_info")
    private SceneInfo sceneInfo;

    /**
     * 最多支持子单条数：50
     * [r]
     */
    @SerializedName("sub_orders")
    private SubOrder subOrders;

    /**
     * 支付者信息
     * [o]
     */
    @SerializedName("combine_payer_info")
    private PayPlayer combinePlayerInfo;

    /**
     * 示例值：2019-12-31T15:59:60+08:00
     * [o]
     */
    @SerializedName("time_start")
    private String timeStart;

    /**
     * 示例值：2019-12-31T15:59:60+08:00
     * [o]
     */
    @SerializedName("time_expire")
    private String timeExpire;

    /**
     * 接收微信支付异步通知回调地址，通知url必须为直接可访问的URL，不能携带参数。
     * 格式: URL
     * 示例值：https://yourapp.com/notify
     * [r]
     */
    @SerializedName("notify_url")
    private String notifyUrl;

    public CPrepayOrder() {
    }

    public String getCombineAppId() {
        return combineAppId;
    }

    public CPrepayOrder setCombineAppId(String combineAppId) {
        this.combineAppId = combineAppId;
        return this;
    }

    public String getCombineMchId() {
        return combineMchId;
    }

    public CPrepayOrder setCombineMchId(String combineMchId) {
        this.combineMchId = combineMchId;
        return this;
    }

    public String getCombineOutTradeNo() {
        return combineOutTradeNo;
    }

    public CPrepayOrder setCombineOutTradeNo(String combineOutTradeNo) {
        this.combineOutTradeNo = combineOutTradeNo;
        return this;
    }

    public SceneInfo getSceneInfo() {
        return sceneInfo;
    }

    public CPrepayOrder setSceneInfo(SceneInfo sceneInfo) {
        this.sceneInfo = sceneInfo;
        return this;
    }

    public SubOrder getSubOrders() {
        return subOrders;
    }

    public CPrepayOrder setSubOrders(SubOrder subOrders) {
        this.subOrders = subOrders;
        return this;
    }

    public PayPlayer getCombinePlayerInfo() {
        return combinePlayerInfo;
    }

    public CPrepayOrder setCombinePlayerInfo(PayPlayer combinePlayerInfo) {
        this.combinePlayerInfo = combinePlayerInfo;
        return this;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public CPrepayOrder setTimeStart(String timeStart) {
        this.timeStart = timeStart;
        return this;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public CPrepayOrder setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
        return this;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public CPrepayOrder setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }
}
