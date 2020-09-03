package com.jk.wxpay.v3.commons.exception;

/**
 * 微信详细错误描述
 */
public class WxErrorDetail {

    private String field;
    private String value;
    private String issue;
    private String location;

    public WxErrorDetail(String field, String value, String issue, String location) {
        this.field = field;
        this.value = value;
        this.issue = issue;
        this.location = location;
    }

    public WxErrorDetail() {
    }

    public String getField() {
        return field;
    }

    public WxErrorDetail setField(String field) {
        this.field = field;
        return this;
    }

    public String getValue() {
        return value;
    }

    public WxErrorDetail setValue(String value) {
        this.value = value;
        return this;
    }

    public String getIssue() {
        return issue;
    }

    public WxErrorDetail setIssue(String issue) {
        this.issue = issue;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public WxErrorDetail setLocation(String location) {
        this.location = location;
        return this;
    }
}
