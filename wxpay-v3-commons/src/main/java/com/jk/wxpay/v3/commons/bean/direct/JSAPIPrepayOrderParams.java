package com.jk.wxpay.v3.commons.bean.direct;

/**
 * 除付款码支付场景以外，商户系统先调用该接口在微信支付服务后台生成预支付交易单，
 * 返回正确的预支付交易会话标识后再按Native、JSAPI、APP等不同场景生成交易串调起支付
 */
public class JSAPIPrepayOrderParams extends PrepayOrderParams {

    private PayPlayer player;

    public JSAPIPrepayOrderParams() {
    }

    public PayPlayer getPlayer() {
        return player;
    }

    public JSAPIPrepayOrderParams setPlayer(PayPlayer player) {
        this.player = player;
        return this;
    }
}
