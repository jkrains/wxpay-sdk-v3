package com.jk.wxpay.v3.async;


import com.jk.sdk.commons.reactor.ApiContext;
import com.jk.wxpay.v3.commons.bean.direct.JSAPIPrepayOrderParams;
import com.jk.wxpay.v3.commons.bean.direct.OrderAmount;
import com.jk.wxpay.v3.commons.bean.direct.PrepayOrderParams;
import com.jk.wxpay.v3.flux.http.HttpApiContextBuilder;
import com.jk.wxpay.v3.reactor.api.CertificatesDownloader;
import com.jk.wxpay.v3.commons.bean.cert.DecryptCertificateEntity;
import com.jk.wxpay.v3.commons.bean.cert.EncryptCertificateEntity;
import com.jk.wxpay.v3.commons.cert.CertificatesDecoder;
import com.jk.wxpay.v3.commons.util.JsonUtils;
import com.jk.wxpay.v3.reactor.api.direct.OrderClosure;
import com.jk.wxpay.v3.reactor.api.direct.prepay.JsApiPrepay;
import com.jk.wxpay.v3.reactor.api.direct.prepay.NativePrepay;
import com.jk.wxpay.v3.flux.MemoryCacheCertificatesManager;
import com.jk.wxpay.v3.flux.SimpleMerchantPrivateKeyManager;
import com.jk.wxpay.v3.reactor.api.direct.prepay.AppPrepay;
import com.jk.wxpay.v3.reactor.api.direct.query.OrderQuery;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

import java.security.cert.X509Certificate;
import java.util.List;

//@RestController
public class AsyncTestController {

    private ApiContext apiContext;
    private SimpleMerchantPrivateKeyManager privateKeyManager;

    AsyncTestController() {

         this.apiContext = init();
    }

    private ApiContext init() {
        privateKeyManager = new SimpleMerchantPrivateKeyManager()
                .setCertSerialNumber("7D376F80BF37E6AAA795D26B3A84C267E393ADBA")    // 商户证书编号。
                .setPrivateKeyPath("cert/apiclient_key.pem")  // 账户私钥文件
                .setApiV3Key("49b203e4735a45d6830aa4847e8d9f10");   // 商户在微信商户平台上的apiV3key。
        MemoryCacheCertificatesManager certificatesManager = new MemoryCacheCertificatesManager(privateKeyManager);
        ApiContext apiContext = new HttpApiContextBuilder().setMerchantPrivateKeyManager(privateKeyManager)
                .setWxCertificatesManager(certificatesManager).build();
        return apiContext;
    }

    @GetMapping("/getcert/{mchid}")
    public Mono<String> getCertFromWxServer(@PathVariable String mchid) {
        CertificatesDownloader certificatesDownloader = new CertificatesDownloader(apiContext);
        Mono<EncryptCertificateEntity> certificates = certificatesDownloader.getCertificates(mchid);
        return certificates.map(ecf -> {
            CertificatesDecoder decoder = new CertificatesDecoder("49b203e4735a45d6830aa4847e8d9f10", ecf);
            DecryptCertificateEntity decryptCertificateEntity = decoder.decodeToEntity();
            List<X509Certificate> certificateList = decoder.decodeToX509List();
            String s = JsonUtils.toJson(ecf);
            return s;
        }).onErrorResume(e -> Mono.just(e.getMessage()));
    }

    @GetMapping("/pay")
    public Mono<String> pay() {
        NativePrepay prepay = new NativePrepay(this.apiContext);
        return prepay.prepay(new PrepayOrderParams()
                .setAppId("wx606378eeb711ffad")
                .setMchId("1542918631")
                .setOutTradeNo("1222222xxx")
                .setDescription("测试")
                .setNotifyUrl("http://test.cms.jikeyiyong.com/wxpay-notify")
                .setAmount(new OrderAmount().setTotal(1))
        ).map(result -> {
            return JsonUtils.toJson(result);
        });
    }

    private void testAppPrepay() {

        AppPrepay appPrepay  = new AppPrepay(this.apiContext);
        PrepayOrderParams prepayOrder = new PrepayOrderParams()
                .setMchId("xxx")
                .setAppId("xx")
                .setOutTradeNo("xxxxxxxx");
        appPrepay.prepay(prepayOrder).subscribe(result -> {
            // do something.
        }, e -> System.out.println());
    }

    private void testNativePrepay() {
        NativePrepay nativePrepay = new NativePrepay(this.apiContext);
        PrepayOrderParams prepayOrder = new PrepayOrderParams()
                .setMchId("xxx")
                .setAppId("xx")
                .setOutTradeNo("xxxxxxxx");
        nativePrepay.prepay(prepayOrder).subscribe(result -> {
            // do something.
        }, e -> System.out.println());
    }

    private void testJSAPIPrepay() {
        JsApiPrepay jsApiPrepay = new JsApiPrepay(this.apiContext);
        JSAPIPrepayOrderParams prepayOrder = new JSAPIPrepayOrderParams();
        prepayOrder.setMchId("xxx").setAppId("111");
        jsApiPrepay.prepay(prepayOrder).subscribe(result -> {
            // do something.
        }, e -> System.out.println());
    }

    private void closeOrder() {
        OrderClosure orderClosure = new OrderClosure(this.apiContext);
        orderClosure.close("merchantId", "outTradeNo").subscribe((v) -> {
            // do something.
        }, e -> System.out.println());
    }

}
