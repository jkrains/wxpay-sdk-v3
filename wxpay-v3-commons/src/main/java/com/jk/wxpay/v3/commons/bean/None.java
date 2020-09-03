package com.jk.wxpay.v3.commons.bean;

/**
 * 这个对象用于标识， 系统正常结束，无返回值，为了填充。
 */
public class None {
    private String none = "none";

    public None() {
    }

    public String getNone() {
        return none;
    }

    public None setNone(String none) {
        this.none = none;
        return this;
    }
}
