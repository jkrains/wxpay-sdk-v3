package com.jk.wxpay.v3.commons.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxErrorDetail;
import com.jk.wxpay.v3.commons.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * json 的转换，默认采用gson.
 */
public class JsonUtils {
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class.getSimpleName());
    public static ObjectMapper mapper = new ObjectMapper();

    static {
        // 对于空的对象转json的时候不抛出错误
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 允许属性名称没有引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 设置输入时忽略在json字符串中存在但在java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 设置输出时包含属性的风格
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }

        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("[{}] toJsonString error：{{}}", obj.getClass().getSimpleName(), e);
        }
        return json;
    }

    public static <T> T fromJson(String jstr, Class<T> clazz) {
        T t = null;
        try {
            t = mapper.readValue(jstr, clazz);
        } catch (Exception e) {
            log.error(" parse json [{}] to class [{}] error：{{}}", jstr, clazz.getSimpleName(), e);
        }
        return t;
    }


    /**
     * 根据微信官方的错误类型，使用gson 解析出错误 string, 并转换成对象。
     * @param statusCode
     * @param errorJson
     * @return
     */
    public static WxErrorException parseError(int statusCode, String errorJson)  {
        log.info("statusCode: " + statusCode + "; errorJson: " + errorJson);
        // error, need to returns error.
        try {
            JsonNode jsonElement = mapper.readTree(errorJson);
            if (jsonElement.isContainerNode()) {
                String code = jsonElement.path("code").asText();
                String message = jsonElement.path("message").asText();
                WxErrorException errorException = new WxErrorException();
                errorException.setScode(statusCode);
                errorException.setCode(code);
                errorException.setMessage(message);
                if (jsonElement.has("detail")) {
                    JsonNode jsonDetails = jsonElement.get("detail");
                    if (jsonDetails.isContainerNode()) {
                        WxErrorDetail detail = new WxErrorDetail();
                        detail.setField(jsonDetails.get("field").asText());
                        detail.setValue(jsonDetails.get("value").asText());
                        detail.setIssue(jsonDetails.get("issue").asText());
                        detail.setIssue(jsonDetails.get("location").asText());
                        errorException.setDetail(detail);
                    }
                }
                return errorException;
            } else {
                return new WxErrorException(WxErrorCode.NOT_SUPPORTED_TYPE, "other error json object is not supported");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new WxErrorException(WxErrorCode.PARAM_ERROR, "parse errorJson failed");
        }
    }
}
