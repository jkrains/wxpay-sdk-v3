package com.jk.wxpay.v3.async;

import com.jk.sdk.commons.block.ApiContext;
import com.jk.wxpay.v3.apache.MemoryCacheCertificatesManager;
import com.jk.wxpay.v3.apache.SimpleMerchantPrivateKeyManager;
import com.jk.wxpay.v3.apache.http.ApiContextBuilder;
import com.jk.wxpay.v3.block.api.CertificatesDownloader;
import com.jk.wxpay.v3.block.api.direct.OrderClosure;
import com.jk.wxpay.v3.block.api.direct.prepay.AppPrepay;
import com.jk.wxpay.v3.block.api.direct.prepay.JsApiPrepay;
import com.jk.wxpay.v3.block.api.direct.prepay.NativePrepay;
import com.jk.wxpay.v3.block.api.direct.query.OrderInquiry;

import com.jk.wxpay.v3.commons.bean.cert.DecryptCertificateEntity;
import com.jk.wxpay.v3.commons.bean.cert.EncryptCertificateEntity;
import com.jk.wxpay.v3.commons.bean.direct.JSAPIPrepayOrderParams;
import com.jk.wxpay.v3.commons.bean.direct.OrderAmount;
import com.jk.wxpay.v3.commons.bean.direct.PrepayOrderParams;
import com.jk.wxpay.v3.commons.bean.direct.result.NativePrepayResult;
import com.jk.wxpay.v3.commons.bean.direct.result.OrderQueryResult;
import com.jk.wxpay.v3.commons.bean.direct.result.PrepayResult;
import com.jk.wxpay.v3.commons.cert.CertificatesDecoder;
import com.jk.wxpay.v3.commons.exception.WxErrorException;

import com.jk.wxpay.v3.commons.util.JsonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.cert.X509Certificate;
import java.util.List;

@RestController
public class SyncTestController {
    private ApiContext apiContext;
    SimpleMerchantPrivateKeyManager privateKeyManager;
    MemoryCacheCertificatesManager certificatesManager;

    public SyncTestController() {
        init();
    }

    private ApiContext init() {
        privateKeyManager = new SimpleMerchantPrivateKeyManager()
                .setCertSerialNumber("7D376F80BF37E6AAA795D26B3A84C267E393ADBA")    // 商户证书编号。
                .setPrivateKeyPath("cert/apiclient_key.pem")  // 账户私钥文件
                .setApiV3Key("49b203e4735a45d6830aa4847e8d9f10");   // 商户在微信商户平台上的apiV3key。
        certificatesManager = new MemoryCacheCertificatesManager(privateKeyManager);
        this.apiContext = new ApiContextBuilder().setMerchantPrivateKeyManager(privateKeyManager)
                .setWxCertificatesManager(certificatesManager).build();
        return apiContext;
    }

    @GetMapping("/getcert/{mchid}")
    public String getCertFromWxServer(@PathVariable String mchid) {
        try {
            CertificatesDownloader certificatesDownloader = new CertificatesDownloader(apiContext);
            EncryptCertificateEntity certificates = certificatesDownloader.getCertificates(mchid);
            CertificatesDecoder decoder = new CertificatesDecoder("49b203e4735a45d6830aa4847e8d9f10", certificates);
            DecryptCertificateEntity decryptCertificateEntity = decoder.decodeToEntity();
            List<X509Certificate> certificateList = decoder.decodeToX509List();
            String s = JsonUtils.toJson(certificates);
            return s;
        } catch (WxErrorException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/pay")
    public String Pay() {
        NativePrepay prepay = new NativePrepay(this.apiContext);
        NativePrepayResult result = prepay.prepay(new PrepayOrderParams()
                .setAppId("wx606378eeb711ffad")
                .setMchId("1542918631")
                .setOutTradeNo("1222222xxx")
                .setDescription("测试")
                .setNotifyUrl("http://test.cms.jikeyiyong.com/wxpay-notify")
                .setAmount(new OrderAmount().setTotal(1)));
        return JsonUtils.toJson(result);
    }

    private void testAppPrepay() {

        try {
            AppPrepay appPrepay  = new AppPrepay(this.apiContext);
            PrepayResult result = appPrepay.prepay(new PrepayOrderParams()
                    .setMchId("xxx")
                    .setAppId("xx").setOutTradeNo("xxxxxxxx"));
            System.out.println(result);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

    }

    private void testNativePrepay() {
        try {
            NativePrepay nativePrepay = new NativePrepay(this.apiContext);
            NativePrepayResult result = nativePrepay.prepay(new PrepayOrderParams()
                    .setMchId("xxx")
                    .setAppId("xx").setOutTradeNo("xxxxxxxx"));
            System.out.println(result);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    private void testJSAPIPrepay() {
        try {
            JsApiPrepay jsApiPrepay = new JsApiPrepay(this.apiContext);
            JSAPIPrepayOrderParams prepayOrder = new JSAPIPrepayOrderParams();
            prepayOrder.setMchId("xxx").setAppId("111");
            PrepayResult result = jsApiPrepay.prepay(prepayOrder);
            System.out.println(result);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

    }

    private void closeOrder() {
        try {
            new OrderClosure(this.apiContext)
                    .close("merchantId", "outTradeNo");
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    private void queryOrder() {
        try {
            OrderInquiry orderInquiry = new OrderInquiry(this.apiContext);
            OrderQueryResult orderQueryResult1 = orderInquiry.queryByOutTradeNo("mchId", "outTradeNo");
            OrderQueryResult orderQueryResult2 = orderInquiry.queryByWechatTransactionId("mchId", "transactionId");

        } catch (WxErrorException e) {
            e.printStackTrace();
        }

    }
}
