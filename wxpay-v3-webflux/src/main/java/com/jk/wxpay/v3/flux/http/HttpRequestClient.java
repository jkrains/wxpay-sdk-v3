package com.jk.wxpay.v3.flux.http;

import com.jk.sdk.commons.reactor.RequestClient;
import com.jk.sdk.commons.reactor.RequestMethod;
import com.jk.wxpay.v3.commons.exception.StatusCode;
import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxErrorException;
import com.jk.wxpay.v3.commons.util.JsonUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 一个实现了http web请求的类。基于Webflux的WebClient来实现。
 */
public class HttpRequestClient implements RequestClient {

    private final WebClient webClient;
    public HttpRequestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    private HttpMethod requestMethodToHttpMethod(RequestMethod requestMethod) {
        if (requestMethod == RequestMethod.GET) {
            return HttpMethod.GET;
        } else if (requestMethod == RequestMethod.POST) {
            return HttpMethod.POST;
        } else if (requestMethod == RequestMethod.PATCH) {
            return HttpMethod.PATCH;
        } else if (requestMethod == requestMethod.PUT) {
            return HttpMethod.PUT;
        } else  if (requestMethod == requestMethod.DELETE) {
            return HttpMethod.DELETE;
        }
        throw new WxErrorException(WxErrorCode.ILLEGAL_ARG, "unknown method");
    }

    private WebClient.RequestBodySpec getRequestBodySpec(
            RequestMethod method,
            MediaType mediaType,
            String subPath,
            Map<String, Object> params,
            Map<String, String> headers,
            Object body) {

        WebClient.RequestBodySpec requestBodySpec = this.webClient
                .method(requestMethodToHttpMethod(method))
                .uri(urlBuilder -> {
                    urlBuilder.path(subPath);
                    if (params != null) {
                        params.forEach((k, v) -> urlBuilder.queryParam(k, v));
                    }
                    return urlBuilder.build();
                })
                .headers(hs -> {
                    hs.add("Accept", "application/json");
                    if (headers != null) {
                        headers.entrySet().forEach(entry -> {
                            hs.add(entry.getKey(), entry.getValue());
                        });
                    }
                })
                .contentType(mediaType)
                .acceptCharset(StandardCharsets.UTF_8);

        if (body != null) {
            String bodyStr = null;
            if (body != null) {
                if (body instanceof String) {
                    bodyStr = (String) body;
                } else {
                    throw new WxErrorException(WxErrorCode.ILLEGAL_ARG, "Body format is not supported");
                }
            }
            requestBodySpec.bodyValue(bodyStr);
        }
        return requestBodySpec;
    }

    @Override
    public Mono<Object> request(
            RequestMethod method,
            String subPath,
            Map<String, Object> params,
            Map<String, String> headers,
            Object body) {
        WebClient.RequestBodySpec requestBodySpec = getRequestBodySpec(method, MediaType.APPLICATION_JSON, subPath, params, headers, body);
        return requestBodySpec
                .exchangeToMono(clientResponse -> {
                    return clientResponse.bodyToMono(String.class)
                            .map(jstr -> {
                                int st = clientResponse.rawStatusCode();
                                return new ResponseHolder(jstr, st);
                            });
                })
                .switchIfEmpty(Mono.just(new ResponseHolder(null, StatusCode.ST_NO_CONTENT)))
                .flatMap(holder -> {
                    if (holder.statusCode == StatusCode.ST_NO_CONTENT) {
                        return Mono.empty();
                    } else {
                        if (holder.statusCode == StatusCode.ST_OK) {
                            return Mono.just(holder.jsonString);
                        } else {
                            return Mono.error(JsonUtils.parseError(holder.statusCode, holder.jsonString));
                        }
                    }
                });


    }

    @Override
    public boolean available() {
        if (this.webClient != null) {
            return true;
        }
        return false;
    }

    class ResponseHolder {
        public final String jsonString;
        public final int statusCode;
        ResponseHolder(String jstr, int st) {
            this.jsonString = jstr;
            this.statusCode = st;
        }
    }
}
