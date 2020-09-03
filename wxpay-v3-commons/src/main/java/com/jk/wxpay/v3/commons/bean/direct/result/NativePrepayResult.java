package com.jk.wxpay.v3.commons.bean.direct.result;

import com.google.gson.annotations.SerializedName;

/**
 * Native 支付的返回结果。
 */
public class NativePrepayResult {

    /**
     * 此URL用于生成支付二维码，然后提供给用户扫码支付。
     * 注意：code_url并非固定值，使用时按照URL格式转成二维码即可。
     * 示例值：weixin://wxpay/bizpayurl/up?pr=NwY5Mz9&groupid=00
     */
    @SerializedName("code_url")
    private String codeUrl;

    public NativePrepayResult() {
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}
