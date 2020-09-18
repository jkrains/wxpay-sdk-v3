package com.jk.wxpay.v3.commons.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxErrorDetail;
import com.jk.wxpay.v3.commons.exception.WxPayException;

/**
 * json 的转换，默认采用gson.
 */
public class JsonUtils {
    private static Gson gson;
    static {
        gson = new Gson();
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String jstr, Class<T> clss) {
        return gson.fromJson(jstr, clss);
    }

    /**
     * 根据微信官方的错误类型，使用gson 解析出错误 string, 并转换成对象。
     * @param statusCode
     * @param errorJson
     * @return
     */
    public static WxPayException parseError(int statusCode, String errorJson) {
        // error, need to returns error.
        JsonElement jsonElement = JsonParser.parseString(errorJson);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String code = jsonObject.get("code").getAsString();
            String message = jsonObject.get("message").getAsString();
            WxPayException errorException = new WxPayException();
            errorException.setScode(statusCode);
            errorException.setCode(code);
            errorException.setMessage(message);
            if (jsonObject.has("detail")) {
                JsonElement detailElement = jsonObject.get("detail");
                if (detailElement.isJsonObject()) {
                    JsonObject jsonDetails = detailElement.getAsJsonObject();
                    WxErrorDetail detail = new WxErrorDetail();
                    detail.setField(jsonDetails.get("field").getAsString());
                    detail.setValue(jsonDetails.get("value").getAsString());
                    detail.setIssue(jsonDetails.get("issue").getAsString());
                    detail.setIssue(jsonDetails.get("location").getAsString());
                    errorException.setDetail(detail);
                }
            }
            return errorException;
        } else {
            return new WxPayException(WxErrorCode.NOT_SUPPORTED_TYPE, "other error json object is not supported");
        }
    }
}
