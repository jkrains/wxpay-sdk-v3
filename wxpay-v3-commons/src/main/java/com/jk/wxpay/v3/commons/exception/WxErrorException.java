package com.jk.wxpay.v3.commons.exception;

/**
 * 微信异常处理类。
 */
public class WxErrorException extends RuntimeException {

    /**
     * 状态码， http的状态码。
     */
    private int scode;
    private String code;
    private String message;
    private WxErrorDetail detail;

    public WxErrorException(int scode, String code, String message, WxErrorDetail detail) {
        super(message);
        this.scode =  scode;
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public WxErrorException(int scode, String code, String message) {
        super(message);
        this.scode = scode;
        this.code = code;
        this.message = message;
    }

    public WxErrorException(String code, String message, WxErrorDetail detail) {
        this(0, code, message, detail);
    }

    public WxErrorException(String code, String message) {
        this(0, code, message);
    }

    public WxErrorException(String message) {
        super(message);
    }

    public WxErrorException() {}

    public int getScode() {
        return scode;
    }

    public WxErrorException setScode(int scode) {
        this.scode = scode;
        return this;
    }

    public String getCode() {
        return code;
    }

    public WxErrorException setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public WxErrorException setMessage(String message) {
        this.message = message;
        return this;
    }

    public WxErrorDetail getDetail() {
        return detail;
    }

    public WxErrorException setDetail(WxErrorDetail detail) {
        this.detail = detail;
        return this;
    }
}
