package com.jk.wxpay.v3.commons.bean.direct;

import com.google.gson.annotations.SerializedName;

/**
 * 预付订单信息。
 */
public class PrepayOrder {

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
     * 商品描述
     * 示例值：Image形象店-深圳腾大-QQ公仔
     * [r]
     */
    private String description;

    /**
     * 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一，详见【商户订单号】。
     * 示例值：1217752501201407033233368018
     * [r]
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     *订单失效时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，
     * YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区
     * （+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，
     * 北京时间2015年5月20日 13点29分35秒。
     * 示例值：2018-06-08T10:34:56+08:00
     * [o]
     */
    @SerializedName("time_expire")
    private String timeExpire;

    /**
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
     * 示例值：自定义数据
     * [o]
     */
    private String attach;

    /**
     *通知URL必须为直接可访问的URL，不允许携带查询串。
     * 格式：URL
     * 示例值：https://www.weixin.qq.com/wxpay/pay.php
     * [r]
     */
    @SerializedName("notify_url")
    private String notifyUrl;

    /**
     * 订单优惠标记
     * 示例值：WXG
     * [o]
     */
    @SerializedName("goods_tag")
    private String goodsTag;

    /**
     * 订单金额信息
     * [r]
     */
    private OrderAmount amount;

    /**
     * 优惠功能
     * [o]
     */
    private DiscountDetail detail;

    /**
     * 支付场景描述
     * [o]
     */
    @SerializedName("scene_info")
    private SceneInfo sceneInfo;

    public PrepayOrder() {
    }

    public String getAppId() {
        return appId;
    }

    public PrepayOrder setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getMchId() {
        return mchId;
    }

    public PrepayOrder setMchId(String mchId) {
        this.mchId = mchId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PrepayOrder setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public PrepayOrder setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public PrepayOrder setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
        return this;
    }

    public String getAttach() {
        return attach;
    }

    public PrepayOrder setAttach(String attach) {
        this.attach = attach;
        return this;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public PrepayOrder setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public PrepayOrder setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
        return this;
    }

    public OrderAmount getAmount() {
        return amount;
    }

    public PrepayOrder setAmount(OrderAmount amount) {
        this.amount = amount;
        return this;
    }

    public DiscountDetail getDetail() {
        return detail;
    }

    public PrepayOrder setDetail(DiscountDetail detail) {
        this.detail = detail;
        return this;
    }

    public SceneInfo getSceneInfo() {
        return sceneInfo;
    }

    public PrepayOrder setSceneInfo(SceneInfo sceneInfo) {
        this.sceneInfo = sceneInfo;
        return this;
    }
}
