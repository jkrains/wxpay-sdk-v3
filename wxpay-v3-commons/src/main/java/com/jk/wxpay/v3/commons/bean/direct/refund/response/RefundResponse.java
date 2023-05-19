package com.jk.wxpay.v3.commons.bean.direct.refund.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.wxpay.v3.commons.bean.direct.refund.FundsAccountTypes;
import com.jk.wxpay.v3.commons.bean.direct.refund.RefundChannels;
import com.jk.wxpay.v3.commons.bean.direct.refund.RefundStatus;

import java.util.List;

/**
 * 退款返回参数。
 */
public class RefundResponse {

  /**
   * 直连商户的商户号，由微信支付生成并下发。
   * 示例值：1900000100
   */
  @JsonProperty("mchid")
  private String mchId;

  /**
   * +
   * 微信支付退款单号
   */
  @JsonProperty("refund_id")
  private String refundId;

  /**
   * +
   * 商户退款单号
   * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
   */
  @JsonProperty("out_refund_no")
  private String outRefundNo;

  /**
   * +
   * 微信支付交易订单号
   */
  @JsonProperty("transaction_id")
  private String transactionId;

  /**
   * +
   * 原支付交易对应的商户订单号
   */
  @JsonProperty("out_trade_no")
  private String outTradeNo;

  /**
   * +
   * 退款渠道
   */
  @JsonProperty("channel")
  private RefundChannels channel;

  /**
   * +
   * 退款入账账户
   * 取当前退款单的退款入账方，有以下几种情况：
   * 1）退回银行卡：{银行名称}{卡类型}{卡尾号}
   * 2）退回支付用户零钱:支付用户零钱
   * 3）退还商户:商户基本账户商户结算银行账户
   * 4）退回支付用户零钱通:支付用户零钱通
   */
  @JsonProperty("user_received_account")
  private String userReceivedAccount;

  /**
   * -
   * 退款成功时间，当退款状态为退款成功时有返回。
   * 示例值：2020-12-01T16:18:12+08:00
   */
  @JsonProperty("success_time")
  private String successTime;

  /**
   * +
   * 退款受理时间
   * 示例值：2020-12-01T16:18:12+08:00
   */
  @JsonProperty("create_time")
  private String createTime;

  /**
   * +
   * 退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台-交易中心，手动处理此笔退款。
   * 枚举值：
   * SUCCESS：退款成功
   * CLOSED：退款关闭
   * PROCESSING：退款处理中
   * ABNORMAL：退款异常
   * 示例值：SUCCESS
   */
  @JsonProperty("status")
  private RefundStatus status;

  /**
   * -
   * 资金账户
   */
  @JsonProperty("funds_account")
  private FundsAccountTypes fundsAccount;

  /**
   * +
   * 金额详细信息
   */
  @JsonProperty("amount")
  private RefundRespAmount amount;

  /**
   * -
   * 优惠退款信息
   */
  @JsonProperty("promotion_detail")
  private List<RefundRespPromotionDetail> promotionDetail;

  public RefundResponse() {
  }

  public String getMchId() {
    return mchId;
  }

  public RefundResponse setMchId(String mchId) {
    this.mchId = mchId;
    return this;
  }

  public String getRefundId() {
    return refundId;
  }

  public RefundResponse setRefundId(String refundId) {
    this.refundId = refundId;
    return this;
  }

  public String getOutRefundNo() {
    return outRefundNo;
  }

  public RefundResponse setOutRefundNo(String outRefundNo) {
    this.outRefundNo = outRefundNo;
    return this;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public RefundResponse setTransactionId(String transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public RefundResponse setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
    return this;
  }

  public RefundChannels getChannel() {
    return channel;
  }

  public RefundResponse setChannel(RefundChannels channel) {
    this.channel = channel;
    return this;
  }

  public String getUserReceivedAccount() {
    return userReceivedAccount;
  }

  public RefundResponse setUserReceivedAccount(String userReceivedAccount) {
    this.userReceivedAccount = userReceivedAccount;
    return this;
  }

  public String getSuccessTime() {
    return successTime;
  }

  public RefundResponse setSuccessTime(String successTime) {
    this.successTime = successTime;
    return this;
  }

  public String getCreateTime() {
    return createTime;
  }

  public RefundResponse setCreateTime(String createTime) {
    this.createTime = createTime;
    return this;
  }

  public RefundStatus getStatus() {
    return status;
  }

  public RefundResponse setStatus(RefundStatus status) {
    this.status = status;
    return this;
  }

  public FundsAccountTypes getFundsAccount() {
    return fundsAccount;
  }

  public RefundResponse setFundsAccount(FundsAccountTypes fundsAccount) {
    this.fundsAccount = fundsAccount;
    return this;
  }

  public RefundRespAmount getAmount() {
    return amount;
  }

  public RefundResponse setAmount(RefundRespAmount amount) {
    this.amount = amount;
    return this;
  }

  public List<RefundRespPromotionDetail> getPromotionDetail() {
    return promotionDetail;
  }

  public RefundResponse setPromotionDetail(List<RefundRespPromotionDetail> promotionDetail) {
    this.promotionDetail = promotionDetail;
    return this;
  }
}
