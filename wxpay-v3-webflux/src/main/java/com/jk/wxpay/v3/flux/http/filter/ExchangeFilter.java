package com.jk.wxpay.v3.flux.http.filter;


import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxErrorException;
import com.jk.wxpay.v3.commons.util.AuthorizationUtils;
import com.jk.wxpay.v3.flux.http.filter.extractor.RequestExtractor;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.reactor.MerchantPrivateKeyManager;
import com.jk.wxpay.v3.reactor.WxCertificatesManager;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;

/**
 * 拦截器，用于拦截 发送的数据和返回的数据。
 */
public class ExchangeFilter implements ExchangeFilterFunction {


    private final MerchantPrivateKeyManager merchantService;
    private final WxCertificatesManager certificatesService;

    public ExchangeFilter(MerchantPrivateKeyManager merchantService, WxCertificatesManager certificatesService) {
        this.merchantService = merchantService;
        this.certificatesService = certificatesService;
    }

    /**
     * 微信支付Http请求拦截器。 用于拦截请求，并做一些处理。
     * 这个拦截器主要是根据参数生成认证信息。
     * 这里处理认证的信息。
     * 假设body的格式为String类型，所有请求的body都被转换成json格式的string类型。
     * @param request
     * @return
     */
    private Mono<ApplyRequestHolder> applyRequest(ClientRequest request) {
        String mchId = request.headers().getFirst(Constants.JK_MCH_ID);
        URI uri = request.url();
        return this.merchantService.getPrivateKey(mchId).flatMap(merchantInfo -> {
            try {

                String method = request.method().name();
                String rawPath = uri.getRawPath();
                if (uri.getQuery() != null) {
                    rawPath += "?" + uri.getRawQuery();
                }
                String body = "";
                BodyInserter<?, ? super ClientHttpRequest> old = request.body();
                if (!method.toUpperCase().equals("GET")) {
                    body = RequestExtractor.extractBody(request, String.class);
                }

                PrivateKey privateKey = merchantInfo.getPrivateKey();
                String serialNumber = merchantInfo.getSerialNumber();
                String authorizationInfo = AuthorizationUtils.buildAuthorizationInfo(mchId, privateKey, serialNumber, method, rawPath, body);
                ClientRequest newRequest = ClientRequest.from(request)
                        .headers(headers -> {
                            headers.remove(Constants.JK_MCH_ID);
                            headers.add("Authorization", FilterUtils.getSchema() + " " + authorizationInfo);
                        })
                        .build();
                return Mono.just(new ApplyRequestHolder(newRequest, mchId));
            } catch (NoSuchAlgorithmException e) {
                return Mono.error(new WxErrorException(WxErrorCode.NO_SUCH_ALGORITHM, e.getMessage()));
            } catch (InvalidKeyException e) {
                return Mono.error(new WxErrorException(WxErrorCode.INVALID_KEY, e.getMessage()));
            } catch (SignatureException e) {
                return Mono.error(new WxErrorException(WxErrorCode.SIGNATURE_EXCEPTION, e.getMessage()));
            }
        });
    }

    /**
     * 
     * 这里处理 微信侧返回数据, 并进行校验。
     * 微信支付响应 拦截器，拦截数据 进行校验。
     * 校验请求的返回结果是否合法。
     * @param mchId  商户id
     * @param response
     * @return
     */
    private Mono<ClientResponse> applyResponse(String mchId, ClientResponse response) {
        int statusCode = response.rawStatusCode();
        if (statusCode >=200 && statusCode < 300) {
            if (this.certificatesService != null) {
                return this.certificatesService.getValidCertificate(mchId).flatMap(certificate -> {
                    return FilterUtils.checkResponse(certificate, response);
                });
            } else {
                // 如果没有设置这个值，则不进行验签。
                return Mono.just(response);
            }

        } else {
            return Mono.just(response);
        }
    }

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {

        URI uri = request.url();
        if (request.headers().containsKey(Constants.JK_MCH_ID)) {

            if (uri.getHost().endsWith(Constants.WX_BASE_URL_SUFFIX)) {
                return this.applyRequest(request)
                        .flatMap(holder -> {
                            return next.exchange(holder.clientRequest).flatMap(response -> {
                                return applyResponse(holder.mchId, response);
                            });
                        });
            } else {
                return next.exchange(request);
            }
        } else {
            return Mono.error(new WxErrorException(WxErrorCode.ILLEGAL_ARG, "Http header should contains jk_mchId"));
        }
    }

    private class ApplyRequestHolder {
        public final ClientRequest clientRequest;
        public final String mchId;
        public ApplyRequestHolder(ClientRequest requestClient, String mchId) {
            this.clientRequest = requestClient;
            this.mchId = mchId;
        }

    }
}
