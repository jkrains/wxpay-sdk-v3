package com.jk.wxpay.v3.commons.util;

import com.jk.wxpay.v3.commons.Constants;

import java.util.HashMap;
import java.util.Map;

public class RequestUtils {

    /**
     * 创建一个params， 并填充mchid.
     * @param mchId
     * @return
     */
    public static Map<String, Object> createParamsWith(String mchId) {
        Map<String, Object> params = new HashMap<>();
        params.put("mchid", mchId);
        return params;
    }

    /**
     * 创建一个header, 并填充mchId 到 Constants.JK_MCH_ID
     * @param mchId
     * @return
     */
    public static Map<String, String> createHeadersWith(String mchId) {
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.JK_MCH_ID, mchId);
        return headers;
    }


}
