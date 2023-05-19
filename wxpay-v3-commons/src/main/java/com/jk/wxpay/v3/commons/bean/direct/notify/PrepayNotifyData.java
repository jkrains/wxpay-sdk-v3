package com.jk.wxpay.v3.commons.bean.direct.notify;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 预支付回调通知数据。微信主动通知发起端的数据结构
 */
public class PrepayNotifyData {
    /**
     * 通知的唯一ID
     * 示例值：EV-2018022511223320873
     */
    private String id;

    /**
     * 示例值：2015-05-20T13:29:35+08:00
     */
    @JsonProperty("create_time")
    private String createTime;

    /**
     * 通知的类型，支付成功通知的类型为TRANSACTION.SUCCESS
     * 示例值：TRANSACTION.SUCCESS
     */
    @JsonProperty("event_type")
    private String eventType;

    /**
     * 通知的资源数据类型，支付成功通知为encrypt-resource
     * 示例值：encrypt-resource
     */
    @JsonProperty("resource_type")
    private String resourceType;

    /**
     * 通知资源数据
     * json格式，见示例
     */
    private PrepayNotifyResource resource;

    /**
     * 回调摘要
     * 示例值：支付成功
     */
    private String summary;

    public PrepayNotifyData() {
    }

    public String getId() {
        return id;
    }

    public PrepayNotifyData setId(String id) {
        this.id = id;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public PrepayNotifyData setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getEventType() {
        return eventType;
    }

    public PrepayNotifyData setEventType(String eventType) {
        this.eventType = eventType;
        return this;
    }

    public String getResourceType() {
        return resourceType;
    }

    public PrepayNotifyData setResourceType(String resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public PrepayNotifyResource getResource() {
        return resource;
    }

    public PrepayNotifyData setResource(PrepayNotifyResource resource) {
        this.resource = resource;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public PrepayNotifyData setSummary(String summary) {
        this.summary = summary;
        return this;
    }
}
