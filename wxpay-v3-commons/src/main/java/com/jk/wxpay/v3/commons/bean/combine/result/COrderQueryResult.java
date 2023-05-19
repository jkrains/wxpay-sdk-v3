package com.jk.wxpay.v3.commons.bean.combine.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.wxpay.v3.commons.bean.direct.PayPlayer;
import com.jk.wxpay.v3.commons.bean.direct.SceneInfo;

/**
 * 合单支付, 查询结果描述。
 */
public class COrderQueryResult {

    /**
     * 合单发起方的appid。
     * 示例值：wxd678efh567hg6787
     */
    @JsonProperty("combine_appid")
    private String combineAppId;

    /**
     * 合单发起方商户号。
     * 示例值：1900000109
     */
    @JsonProperty("combine_mchid")
    private String combineMchId;

    /**
     * 合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * 示例值：P20150806125346
     */
    @JsonProperty("combine_out_trade_no")
    private String combineOutTradeNo;

    /**
     * 支付场景信息描述
     */
    @JsonProperty("scene_info")
    private SceneInfo sceneInfo;

    /**
     * 最多支持子单条数：50
     */
    @JsonProperty("sub_orders")
    private SubOrderResult subOrders;

    /**
     * 示例值：见请求示例
     */
    @JsonProperty("combine_payer_info")
    private PayPlayer combinePayerInfo;

    public COrderQueryResult() {
    }

    public String getCombineAppId() {
        return combineAppId;
    }

    public COrderQueryResult setCombineAppId(String combineAppId) {
        this.combineAppId = combineAppId;
        return this;
    }

    public String getCombineMchId() {
        return combineMchId;
    }

    public COrderQueryResult setCombineMchId(String combineMchId) {
        this.combineMchId = combineMchId;
        return this;
    }

    public String getCombineOutTradeNo() {
        return combineOutTradeNo;
    }

    public COrderQueryResult setCombineOutTradeNo(String combineOutTradeNo) {
        this.combineOutTradeNo = combineOutTradeNo;
        return this;
    }

    public SceneInfo getSceneInfo() {
        return sceneInfo;
    }

    public COrderQueryResult setSceneInfo(SceneInfo sceneInfo) {
        this.sceneInfo = sceneInfo;
        return this;
    }

    public SubOrderResult getSubOrders() {
        return subOrders;
    }

    public COrderQueryResult setSubOrders(SubOrderResult subOrders) {
        this.subOrders = subOrders;
        return this;
    }

    public PayPlayer getCombinePayerInfo() {
        return combinePayerInfo;
    }

    public COrderQueryResult setCombinePayerInfo(PayPlayer combinePayerInfo) {
        this.combinePayerInfo = combinePayerInfo;
        return this;
    }
}
