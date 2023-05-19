package com.jk.wxpay.v3.commons.bean.cert;

/**
 * 请求需要验签的参数。
 */
public class VerifyInfo {

  private final String serial;
  private final String timestamp;
  private final String nonce;
  private final String body;
  private final String signature;

  public VerifyInfo(String serial, String timestamp, String nonce, String body, String signature) {
    this.serial = serial;
    this.timestamp = timestamp;
    this.nonce = nonce;
    this.body = body;
    this.signature = signature;
  }

  public String getSerial() {
    return serial;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public String getNonce() {
    return nonce;
  }

  public String getBody() {
    return body;
  }

  public String getSignature() {
    return signature;
  }
}
