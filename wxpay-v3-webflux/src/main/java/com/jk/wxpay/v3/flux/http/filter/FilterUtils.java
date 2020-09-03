package com.jk.wxpay.v3.flux.http.filter;

import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxErrorException;
import com.jk.wxpay.v3.commons.util.ValidationUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;

public class FilterUtils {

    public static final String H_REQUEST_ID  = "Request-ID";
    public static final String H_W_SERIAL    = "Wechatpay-Serial";
    public static final String H_W_SIGNATURE = "Wechatpay-Signature";
    public static final String H_W_TIMESTAMP = "Wechatpay-Timestamp";
    public static final String H_W_NONCE     = "Wechatpay-Nonce";

    public static String getSchema() {
        return "WECHATPAY2-SHA256-RSA2048";
    }

    public static RuntimeException parameterError(String message, Object... args) {
        message = String.format(message, args);
        return new IllegalArgumentException("parameter error: " + message);
    }

    /**
     * 这个函数对微信返回的必要头信息进行校验如果不存在 或不合法，则抛出异常。
     * @param headers
     */
    public static void validateResponseHeaders(HttpHeaders headers) {

        if (!headers.containsKey(H_REQUEST_ID)) {
            throw parameterError("Empty Request-ID");
        }
        String requestId = headers.getFirst(H_REQUEST_ID);

        if (!headers.containsKey(H_W_SERIAL)) {
            throw parameterError("Empty Wechatpay-Serial, request-id=[%s]", requestId);
        }

        if (!headers.containsKey(H_W_SIGNATURE)) {
            throw parameterError("Empty Wechatpay-Signature, request-id=[%s]", requestId);
        }

        if (!headers.containsKey(H_W_TIMESTAMP)) {
            throw parameterError("Empty Wechatpay-Timestamp, request-id=[%s]", requestId);
        }

        if (!headers.containsKey(H_W_NONCE)) {
            throw parameterError("Empty Wechatpay-Nonce, request-id=[%s]", requestId);
        }

        String strTimestamp = headers.getFirst(H_W_TIMESTAMP);
        try {
            Instant instant = Instant.ofEpochSecond(Long.parseLong(strTimestamp));
            if (Duration.between(instant, Instant.now()).abs().toMinutes() > 5) {
                throw parameterError("Timestamp=[%s] expires, request-id=[%s]",
                        instant.toEpochMilli() / 1000, requestId);
            }
        } catch (DateTimeException | NumberFormatException e) {
            throw parameterError("Invalid timestamp=[%s], request-id=[%s]",
                    strTimestamp, requestId);
        }
    }

    /**
     * 校验微信测返回的数据。
     * @param certificate
     * @param response
     * @return
     */
    public static Mono<ClientResponse> checkResponse(X509Certificate certificate, ClientResponse response) {
        HttpHeaders headers = response.headers().asHttpHeaders();
        validateResponseHeaders(headers);

        return Mono.just(response).publishOn(Schedulers.parallel()).flatMap(r -> {
            String timestamp = headers.getFirst(H_W_TIMESTAMP);
            String nonce = headers.getFirst(H_W_NONCE);
            String serial = headers.getFirst(H_W_SERIAL);
            String signature = headers.getFirst(H_W_SIGNATURE);
            return r.toEntity(String.class).map(entity -> {
                String body = entity.toString();
                if (body == null) {
                    body = "";
                }
                try {
                    boolean valid = ValidationUtil.validate(certificate, timestamp, nonce, body, signature);
                    if (valid) {
                        return response;
                    } else {
                        throw new WxErrorException(WxErrorCode.WX_RESPONSE_INVALID, "Validate failed");
                    }
                } catch (NoSuchAlgorithmException e) {
                    throw new WxErrorException(WxErrorCode.NO_SUCH_ALGORITHM, e.getMessage());
                } catch (InvalidKeyException e) {
                    throw new WxErrorException(WxErrorCode.INVALID_KEY, e.getMessage());
                } catch (SignatureException e) {
                    throw new WxErrorException(WxErrorCode.SIGNATURE_EXCEPTION, e.getMessage());
                }
            });
        });
    }
}
