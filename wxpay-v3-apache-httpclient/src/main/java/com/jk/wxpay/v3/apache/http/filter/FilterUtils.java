package com.jk.wxpay.v3.apache.http.filter;

import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxErrorException;
import com.jk.wxpay.v3.commons.util.ValidationUtil;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;

import static com.jk.wxpay.v3.commons.Constants.*;

public class FilterUtils {

    public static String getSchema() {
        return "WECHATPAY2-SHA256-RSA2048";
    }

    public static RuntimeException parameterError(String message, Object... args) {
        message = String.format(message, args);
        return new IllegalArgumentException("parameter error: " + message);
    }

    public static final String getResponseBody(CloseableHttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();

        return (entity != null && entity.isRepeatable()) ? EntityUtils.toString(entity) : "";
    }

    /**
     * 先进行请求参数的校验，如果不是微信返回的，则报错。
     * @param response
     */
    public static final void validateParameters(CloseableHttpResponse response) {
        String requestId;
        if (!response.containsHeader(H_REQUEST_ID)) {
            throw parameterError("empty Request-ID");
        } else {
            requestId = response.getFirstHeader(H_REQUEST_ID).getValue();
        }

        if (!response.containsHeader(H_W_SERIAL)) {
            throw parameterError("empty Wechatpay-Serial, request-id=[%s]", requestId);
        } else if (!response.containsHeader(H_W_SIGNATURE)){
            throw parameterError("empty Wechatpay-Signature, request-id=[%s]", requestId);
        } else if (!response.containsHeader(H_W_TIMESTAMP)) {
            throw parameterError("empty Wechatpay-Timestamp, request-id=[%s]", requestId);
        } else if (!response.containsHeader(H_W_NONCE)) {
            throw parameterError("empty Wechatpay-Nonce, request-id=[%s]", requestId);
        } else {
            Header timestamp = response.getFirstHeader(H_W_TIMESTAMP);
            try {
                Instant instant = Instant.ofEpochSecond(Long.parseLong(timestamp.getValue()));
                // 拒绝5分钟之外的应答
                if (Duration.between(instant, Instant.now()).abs().toMinutes() >= 5) {
                    throw parameterError("timestamp=[%s] expires, request-id=[%s]",
                            timestamp.getValue(), requestId);
                }
            } catch (DateTimeException | NumberFormatException e) {
                throw parameterError("invalid timestamp=[%s], request-id=[%s]",
                        timestamp.getValue(), requestId);
            }
        }
    }
    public static CloseableHttpResponse checkResponse(X509Certificate certificate, CloseableHttpResponse response) {
        validateParameters(response);
        try {
            String timestamp = response.getFirstHeader(H_W_TIMESTAMP).getValue();
            String nonce = response.getFirstHeader(H_W_NONCE).getValue();
            String serial = response.getFirstHeader(H_W_SERIAL).getValue();
            String signature = response.getFirstHeader(H_W_SIGNATURE).getValue();
            String body = getResponseBody(response);
            boolean valid = ValidationUtil.validate(certificate, timestamp, nonce, body, signature);
            if (!valid) {
                throw new WxErrorException(WxErrorCode.WX_RESPONSE_INVALID, "Validate failed");
            } else {
                return response;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new WxErrorException(WxErrorCode.NO_SUCH_ALGORITHM, e.getMessage());
        } catch (InvalidKeyException e) {
            throw new WxErrorException(WxErrorCode.INVALID_KEY, e.getMessage());
        } catch (SignatureException e) {
            throw new WxErrorException(WxErrorCode.SIGNATURE_EXCEPTION, e.getMessage());
        } catch (IOException e) {
            throw new WxErrorException(e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
