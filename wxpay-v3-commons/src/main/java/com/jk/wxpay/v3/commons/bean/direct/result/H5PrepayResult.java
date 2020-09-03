package com.jk.wxpay.v3.commons.bean.direct.result;

import com.google.gson.annotations.SerializedName;

public class H5PrepayResult {

    /**
     * h5_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付，h5_url的有效期为5分钟。
     * 示例值：https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb?prepay_id=wx2016121516420242444321ca0631331346&package=1405458241
     */
    @SerializedName("h5_url")
    private String h5Url;

    public H5PrepayResult() {
    }

    public String getH5Url() {
        return h5Url;
    }

    public H5PrepayResult setH5Url(String h5Url) {
        this.h5Url = h5Url;
        return this;
    }
}
