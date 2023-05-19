package com.jk.wxpay.v3.commons.bean.combine;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.wxpay.v3.commons.bean.RequestParams;
import com.jk.wxpay.v3.commons.bean.direct.PayPlayer;
import com.jk.wxpay.v3.commons.bean.direct.SceneInfo;
import com.jk.wxpay.v3.commons.validation.ValidationUtils;

/**
 * 使用合单支付接口，用户只输入一次密码，即可完成多个订单的支付。目前最多一次可支持50笔订单进行合单支付。
 * 注意：
 * • 订单如果需要进行抽佣等，需要在合单中指定需要进行分账（profit_sharing为true）；指定后，交易资金进入二级商户账户，处于冻结状态，
 * 可在后续使用分账接口进行分账，利用分账完结进行资金解冻，实现抽佣和对二级商户的账期。
 */
public class CPrepayOrderParams implements RequestParams {

    /**
     * 合单发起方的appid。
     * 示例值：wxd678efh567hg6787
     * [r]
     */
    @JsonProperty("combine_appid")
    private String combineAppId;

    /**
     * 合单发起方商户号。
     * 示例值：1900000109
     * [r]
     */
    @JsonProperty("combine_mchid")
    private String combineMchId;

    /**
     * 合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一 。
     * 示例值：P20150806125346
     * [r]
     */
    @JsonProperty("combine_out_trade_no")
    private String combineOutTradeNo;

    /**
     * 支付场景信息描述
     * [o]
     */
    @JsonProperty("scene_info")
    private SceneInfo sceneInfo;

    /**
     * 最多支持子单条数：50
     * [r]
     */
    @JsonProperty("sub_orders")
    private SubOrder subOrders;

    /**
     * 支付者信息
     * [o]
     */
    @JsonProperty("combine_payer_info")
    private PayPlayer combinePlayerInfo;

    /**
     * 示例值：2019-12-31T15:59:60+08:00
     * [o]
     */
    @JsonProperty("time_start")
    private String timeStart;

    /**
     * 示例值：2019-12-31T15:59:60+08:00
     * [o]
     */
    @JsonProperty("time_expire")
    private String timeExpire;

    /**
     * 接收微信支付异步通知回调地址，通知url必须为直接可访问的URL，不能携带参数。
     * 格式: URL
     * 示例值：https://yourapp.com/notify
     * [r]
     */
    @JsonProperty("notify_url")
    private String notifyUrl;

    public CPrepayOrderParams() {
    }

    @Override
    public void validate() throws RuntimeException {
        ValidationUtils.required("combineAppId", combineAppId);
        ValidationUtils.required("combineMchId", combineMchId);
        ValidationUtils.required("combineOutTradeNo", combineOutTradeNo);
        ValidationUtils.required("subOrders", subOrders);
        ValidationUtils.required("notifyUrl", notifyUrl);
    }

    public String getCombineAppId() {
        return combineAppId;
    }

    public CPrepayOrderParams setCombineAppId(String combineAppId) {
        this.combineAppId = combineAppId;
        return this;
    }

    public String getCombineMchId() {
        return combineMchId;
    }

    public CPrepayOrderParams setCombineMchId(String combineMchId) {
        this.combineMchId = combineMchId;
        return this;
    }

    public String getCombineOutTradeNo() {
        return combineOutTradeNo;
    }

    public CPrepayOrderParams setCombineOutTradeNo(String combineOutTradeNo) {
        this.combineOutTradeNo = combineOutTradeNo;
        return this;
    }

    public SceneInfo getSceneInfo() {
        return sceneInfo;
    }

    public CPrepayOrderParams setSceneInfo(SceneInfo sceneInfo) {
        this.sceneInfo = sceneInfo;
        return this;
    }

    public SubOrder getSubOrders() {
        return subOrders;
    }

    public CPrepayOrderParams setSubOrders(SubOrder subOrders) {
        this.subOrders = subOrders;
        return this;
    }

    public PayPlayer getCombinePlayerInfo() {
        return combinePlayerInfo;
    }

    public CPrepayOrderParams setCombinePlayerInfo(PayPlayer combinePlayerInfo) {
        this.combinePlayerInfo = combinePlayerInfo;
        return this;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public CPrepayOrderParams setTimeStart(String timeStart) {
        this.timeStart = timeStart;
        return this;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public CPrepayOrderParams setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
        return this;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public CPrepayOrderParams setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }
}
