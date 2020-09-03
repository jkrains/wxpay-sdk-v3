package com.jk.wxpay.v3.commons.bean.direct;

public class PayPlayer {

    private String openId;

    public PayPlayer(String openId) {
        this.openId = openId;
    }

    public PayPlayer() {
    }

    public String getOpenId() {
        return openId;
    }

    public PayPlayer setOpenId(String openId) {
        this.openId = openId;
        return this;
    }
}
