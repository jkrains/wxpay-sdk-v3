package com.jk.wxpay.v3.apache.http;

import com.jk.sdk.commons.block.RequestClient;
import com.jk.sdk.commons.block.RequestMethod;
import com.jk.wxpay.v3.commons.exception.StatusCode;
import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxErrorException;
import com.jk.wxpay.v3.commons.util.JsonUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * 一个实现了http web请求的类。基于apache httpclient 来实现。
 */
public class HttpRequestClient implements RequestClient {

    private final CloseableHttpClient httpClient;
    private final String hostUrl;
    public HttpRequestClient(String baseUrl, CloseableHttpClient httpClient) {
        this.hostUrl = baseUrl;
        this.httpClient = httpClient;
    }

    @Override
    public Object request(
            RequestMethod method,
            String subPath,
            Map<String, Object> params,
            Map<String, String> headers,
            Object body) {

        try {
            URIBuilder uriBuilder = new URIBuilder(this.hostUrl);
            if (params != null && params.size() > 0) {
                params.entrySet().forEach(entry -> {
                    uriBuilder.addParameter(entry.getKey(), (String)entry.getValue());
                });
            }
            if (subPath != null) {
                uriBuilder.setPath(subPath);
            }

            URI uri = uriBuilder.build();
            HttpUriRequest request = convertToHttpUriRequest(method, uri, body);
            if (headers != null && headers.size() > 0) {
                headers.entrySet().forEach(entry -> {
                    request.setHeader(entry.getKey(), entry.getValue());
                });
            }
            request.setHeader("Accept", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == StatusCode.ST_OK) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                return content;
            } else if (statusCode == StatusCode.ST_NO_CONTENT) {
                return null;
            } else {
                // error do errors.
                String error = EntityUtils.toString(response.getEntity(), "UTF-8");
                throw JsonUtils.parseError(statusCode, error);
            }
        } catch (URISyntaxException e ) {
            throw new WxErrorException(e.getClass().getSimpleName(), e.getMessage());
        } catch (ClientProtocolException e) {
            throw new WxErrorException(e.getClass().getSimpleName(), e.getMessage());
        } catch (IOException e) {
            throw new WxErrorException(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    private HttpUriRequest convertToHttpUriRequest(RequestMethod method, URI uri, Object body) {

        HttpUriRequest request = null;

        if (method == RequestMethod.GET) {
            HttpGet httpGet = new HttpGet(uri);
            request = httpGet;
        } else if (method == RequestMethod.POST) {
            HttpPost httpPost = new HttpPost(uri);
            if (body != null && body instanceof String) {
                httpPost.setEntity(new StringEntity((String)body, ContentType.APPLICATION_JSON));
            }
            request = httpPost;
        } else if (method == RequestMethod.PATCH) {
            HttpPatch httpPatch = new HttpPatch(uri);
            if (body != null && body instanceof String) {
                httpPatch.setEntity(new StringEntity((String)body, ContentType.APPLICATION_JSON));
            }
            request = httpPatch;
        } else if (method == RequestMethod.PUT) {
            HttpPut httpPut = new HttpPut(uri);
            if (body != null && body instanceof String) {
                httpPut.setEntity(new StringEntity((String)body, ContentType.APPLICATION_JSON));
            }
            request = httpPut;
        } else  if (method == RequestMethod.DELETE) {
            HttpDelete httpDelete = new HttpDelete(uri);
            request = httpDelete;
        } else {
            throw new WxErrorException(WxErrorCode.ILLEGAL_ARG, "unknown method");
        }

        return request;
    }

    @Override
    public boolean available() {
        if (this.httpClient != null) {
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
