# wxpay-sdk说明
微信支付v3版本的sdk, 目前包含同步API和异步API。用户可以使用此API 版本在传统的命令式编程模式上，也可以使用异步API用于响应式编程。
目前只支持微信 ==直连模式== 支付。

## 同步API

用于传统命令式编程。 可以用于Spring MVC 或其他框架。目前httpClient 采用Apache-httpClient。

## 异步API

同时，我们也支持了异步接口，异步实现采用Project reactor。httpClient采用Spring Webflux的WebClient。 

## 模块说明

- wxpay-v3-commons
  微信v3接口的公共类定义， 同步异步均继承这个类，抽象了一些支付需要的公共方法和类放在这个模块中。
- wxpay-v3-reactor
  微信v3的SDK, 基于ProjectReactor 实现了一些支付类。 可以不在spring环境下使用。
- wxpay-v3-webflux
  微信v3的SDK, 基于webflux来实现。 适用于spring环境。 用户可以用于异步或同步模式。 签名函数在拦截器中实现。

# 注意
- 该模块只支持微信v3版本的SDK. 其他v1, v2版本不支持。 
- 在支付接口调用中， 基于Webflux实现的支付接口，传到拦截器(WxPayExchangeFilter)中的请求，头部必须包含"jk_mchId", 用于标明是那个商户发起的请求。
- 对于JSON的解析，本SDK 依赖于Gson来解析。

# roadmap
1. 后续支持服务商模式。
