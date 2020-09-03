package com.jk.wxpay.v3.commons.bean.direct;

import com.google.gson.annotations.SerializedName;

/**
 * 商户门店信息
 */
public class StoreInfo {
    /**
     * 商户侧门店编号
     * 示例值：0001
     */
    private String id;

    /**
     * 商户侧门店名称
     * 示例值：腾讯大厦分店
     */
    private String name;

    /**
     * 地区编码，详细请见省市区编号对照表。
     */
    @SerializedName("area_code")
    private String areaCode;

    /**
     * 地区编码，详细请见省市区编号对照表。
     */
    private String address;

    public StoreInfo(String id, String name, String areaCode, String address) {
        this.id = id;
        this.name = name;
        this.areaCode = areaCode;
        this.address = address;
    }

    public StoreInfo() {
    }

    public String getId() {
        return id;
    }

    public StoreInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StoreInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public StoreInfo setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public StoreInfo setAddress(String address) {
        this.address = address;
        return this;
    }
}
