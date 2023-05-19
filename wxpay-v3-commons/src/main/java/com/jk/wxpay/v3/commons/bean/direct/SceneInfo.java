package com.jk.wxpay.v3.commons.bean.direct;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 场景信息支付场景描述
 */
public class SceneInfo {
    /**
     * 调用微信支付API的机器IP，支持IPv4和IPv4两种格式的IP地址。
     * 示例值：14.23.150.211
     * [r]
     */
    @JsonProperty("player_client_ip")
    private String playerClientIp;

    /**
     * 商户端设备号（门店号或收银设备ID）。
     * 示例值：013467007045764
     * [o]
     */
    @JsonProperty("device_id")
    private String deviceId;

    /**
     * 商户门店信息
     * [o]
     */
    @JsonProperty("store_info")
    private StoreInfo storeInfo;

    public SceneInfo(String playerClientIp, String deviceId, StoreInfo storeInfo) {
        this.playerClientIp = playerClientIp;
        this.deviceId = deviceId;
        this.storeInfo = storeInfo;
    }

    public SceneInfo() {
    }

    public String getPlayerClientIp() {
        return playerClientIp;
    }

    public SceneInfo setPlayerClientIp(String playerClientIp) {
        this.playerClientIp = playerClientIp;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public SceneInfo setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public StoreInfo getStoreInfo() {
        return storeInfo;
    }

    public SceneInfo setStoreInfo(StoreInfo storeInfo) {
        this.storeInfo = storeInfo;
        return this;
    }
}
