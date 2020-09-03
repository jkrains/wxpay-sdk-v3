package com.jk.wxpay.v3.async;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.jk.wxpay.v3.flux.http.filter.WxPayExchangeFilter;
import com.jk.wxpay.v3.flux.service.SimpleMerchantPrivateKeyService;
import com.jk.wxpay.v3.flux.service.SimpleWxCertificatesService;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@RestController
public class TestController {
    private String baseUrl = "https://api.mch.weixin.qq.com";
    private WebClient webClient;

    TestController() {
        WxPayExchangeFilter filter = new WxPayExchangeFilter(new SimpleMerchantPrivateKeyService(), new SimpleWxCertificatesService());
        this.webClient = WebClient.builder()
                .baseUrl(this.baseUrl).filter(filter)
                .build();
    }

    @GetMapping("/test")
    public Mono<String> test() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TestEntity entity = new TestEntity().setName("zhangsan").setCount(12);
            String jstr = objectMapper.writeValueAsString(entity);
            return this.webClient.method(HttpMethod.POST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .acceptCharset(StandardCharsets.UTF_8)
                    .bodyValue(entity)
                    .exchange().map((c) -> "success");

        } catch (JsonProcessingException e) {
            return Mono.just("failed " + e);
        }
    }

    public static class TestEntity  {
        private String name;
        private int count;

        public TestEntity() {
        }

        public String getName() {
            return name;
        }

        public TestEntity setName(String name) {
            this.name = name;
            return this;
        }

        public int getCount() {
            return count;
        }

        public TestEntity setCount(int count) {
            this.count = count;
            return this;
        }

        @Override
        public String toString() {
            return "TestEntity{" +
                    "name='" + name + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}
