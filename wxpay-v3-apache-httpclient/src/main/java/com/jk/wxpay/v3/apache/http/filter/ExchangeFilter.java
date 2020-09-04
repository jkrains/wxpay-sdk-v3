package com.jk.wxpay.v3.apache.http.filter;

import com.jk.wxpay.v3.block.MerchantPrivateKeyManager;
import com.jk.wxpay.v3.block.WxCertificatesManager;
import com.jk.wxpay.v3.commons.Constants;
import com.jk.wxpay.v3.commons.bean.MerchantPrivateKey;
import com.jk.wxpay.v3.commons.exception.WxErrorException;
import com.jk.wxpay.v3.commons.util.AuthorizationUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpExecutionAware;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.execchain.ClientExecChain;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.X509Certificate;

/**
 * 拦截器，拦截 http的请求和响应。
 */
public class ExchangeFilter implements ClientExecChain {

    private  final ClientExecChain mainExec;
    private final MerchantPrivateKeyManager merchantService;
    private final WxCertificatesManager certificatesService;

    public ExchangeFilter(ClientExecChain mainExec, MerchantPrivateKeyManager merchantService, WxCertificatesManager certificatesService) {

        this.mainExec = mainExec;
        this.merchantService = merchantService;
        this.certificatesService = certificatesService;
    }

    @Override
    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request, HttpClientContext clientContext,
                                         HttpExecutionAware execAware) throws IOException, HttpException {
        if (request.getURI().getHost().endsWith(Constants.WX_BASE_URL_SUFFIX)) {
            String mchId = request.getFirstHeader(Constants.JK_MCH_ID).toString();
            applyRequest(mchId, request);
            CloseableHttpResponse response = mainExec.execute(route, request, clientContext, execAware);
            return applyResponse(mchId, response);
        } else {
            return mainExec.execute(route, request, clientContext, execAware);
        }
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
    private CloseableHttpResponse applyResponse(String mchId, CloseableHttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode >=200 && statusCode < 300) {
            if (this.certificatesService != null) {
                X509Certificate validCertificate = this.certificatesService.getValidCertificate(mchId);
                return FilterUtils.checkResponse(validCertificate, response);
            } else {
                // 如果没有wx证书，则不进行验签。
                return response;
            }
        } else {
            return response;
        }
    }

    /**
     * 微信支付Http请求拦截器。 用于拦截请求，并做一些处理。
     * 这个拦截器主要是根据参数生成认证信息。
     * 这里处理认证的信息。
     * 假设body的格式为String类型，所有请求的body都被转换成json格式的string类型。
     * @param wrapper
     * @return
     */
    private void applyRequest(String mchId, HttpRequestWrapper wrapper) {
        try {
            MerchantPrivateKey privateKey = this.merchantService.getPrivateKey(mchId);

            String method = wrapper.getMethod();
            URI uri = wrapper.getURI();
            String rawPath = uri.getRawPath();
            if (uri.getQuery() != null) {
                rawPath += "?" + uri.getRawQuery();
            }
            FilterUtils.convertToRepeatableRequestEntity(wrapper);
            String body;
            if (wrapper.getOriginal() instanceof HttpGet) {
                body = "";
            } else {
                body = EntityUtils.toString(((HttpEntityEnclosingRequest) wrapper).getEntity());
            }
            String authorizationInfo = AuthorizationUtils.buildAuthorizationInfo(mchId, privateKey.getPrivateKey(), privateKey.getSerialNumber(), method, rawPath, body);
            // 添加认证信息
            wrapper.addHeader("Authorization",
                    FilterUtils.getSchema() + " " + authorizationInfo);
        } catch (NoSuchAlgorithmException e) {
            throw new WxErrorException(e.getClass().getSimpleName(), e.getMessage());
        } catch (InvalidKeyException e) {
            throw  new WxErrorException(e.getClass().getSimpleName(), e.getMessage());
        } catch (SignatureException e) {
            throw new WxErrorException(e.getClass().getSimpleName(), e.getMessage());
        } catch (IOException e) {
            throw new WxErrorException(e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
