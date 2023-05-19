package com.jk.wxpay.v3.commons.bean;

import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxErrorException;

import java.util.Arrays;
import java.util.Optional;

/**
 *
 * TradeState的枚举定义
 */
public enum TradeState {

  SUCCESS("SUCCESS"),
  REFUND("REFUND"),
  NOTPAY("NOTPAY"),
  CLOSED("CLOSED"),
  REVOKED("REVOKED"),
  USERPAYING("USERPAYING"),
  PAYERROR("PAYERROR");

  TradeState(String name) {
    this.name = name;
  }
  private String name;

  public String getName() {
    return name;
  }

  public static void validate(String name) {
    Optional<TradeState> first = Arrays.asList(values()).stream().filter(type -> type.name.contentEquals(name)).findFirst();
    if (!first.isPresent()) {
      throw new WxErrorException()
          .setCode(WxErrorCode.ILLEGAL_ARG)
          .setMessage("name=" + name);
    }
  }

  public static TradeState fromName(String name) {
    Optional<TradeState> first = Arrays.asList(values()).stream().filter(type -> type.name.contentEquals(name)).findFirst();
    if (first.isPresent()) {
      return first.get();
    } else {
      return null;
    }
  }
}