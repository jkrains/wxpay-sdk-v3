package com.jk.wxpay.v3.commons.bean.direct.refund;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * 退款参数。
 */
public class RefundParams {

  /**
   * 微信支付订单号
   * 原支付交易对应的微信订单号
   * 二选一
   */
  @JsonProperty("transaction_id")
  private String transactionId;

  /**
   * 二选一
   * 原支付交易对应的商户订单号
   */
  @JsonProperty("out_trade_no")
  private String outTradeNo;

  /**
   * +
   * 商户退款单号
   * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
   * 示例值：1217752501201407033233368018
   */
  @JsonProperty("out_refund_no")
  private String outRefundNo;

  /**
   * -
   * 退款原因
   * 若商户传入，会在下发给用户的退款消息中体现退款原因
   */
  private String reason;

  /**
   * -
   * 退款结果回调url
   * 异步接收微信支付退款结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效，优先回调当前传的这个地址
   */
  @JsonProperty("notify_url")
  private String notifyUrl;

  /**
   * -
   * 退款资金来源
   * 若传递此参数则使用对应的资金账户退款，否则默认使用未结算资金退款（仅对老资金流商户适用）
   * 枚举值：
   * AVAILABLE：可用余额账户
   */
  @JsonProperty("funds_account")
  private String fundsAccount;

  /**
   * +
   * 金额信息
   */
  private RefundAmount amount;

  /**
   * -
   * 退款商品
   * 指定商品退款需要传此参数，其他场景无需传递
   */
  @JsonProperty("goods_detail")
  private RefundGoodsDetail goodsDetail;

  public RefundParams() {
  }

  public String getTransactionId() {
    return transactionId;
  }

  public RefundParams setTransactionId(String transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public RefundParams setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
    return this;
  }

  public String getOutRefundNo() {
    return outRefundNo;
  }

  public RefundParams setOutRefundNo(String outRefundNo) {
    this.outRefundNo = outRefundNo;
    return this;
  }

  public String getReason() {
    return reason;
  }

  public RefundParams setReason(String reason) {
    this.reason = reason;
    return this;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public RefundParams setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
    return this;
  }

  public String getFundsAccount() {
    return fundsAccount;
  }

  public RefundParams setFundsAccount(String fundsAccount) {
    this.fundsAccount = fundsAccount;
    return this;
  }

  public RefundAmount getAmount() {
    return amount;
  }

  public RefundParams setAmount(RefundAmount amount) {
    this.amount = amount;
    return this;
  }

  public RefundGoodsDetail getGoodsDetail() {
    return goodsDetail;
  }

  public RefundParams setGoodsDetail(RefundGoodsDetail goodsDetail) {
    this.goodsDetail = goodsDetail;
    return this;
  }
}
