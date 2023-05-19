package com.jk.wxpay.v3.commons.bean;

import com.jk.wxpay.v3.commons.exception.WxErrorCode;
import com.jk.wxpay.v3.commons.exception.WxErrorException;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * TradeType的枚举定义
 */
public enum TradeType {

  JSAPI("JSAPI"),
  NATIVE("NATIVE"),
  APP("APP"),
  MICROPAY("MICROPAY"),
  MWEB("MWEB"),
  FACEPAY("FACEPAY");

  private String name;

  TradeType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static void validate(String name) {
    Optional<TradeType> first = Arrays.asList(values()).stream().filter(type -> type.name.contentEquals(name)).findFirst();
    if (!first.isPresent()) {
      throw new WxErrorException()
          .setCode(WxErrorCode.ILLEGAL_ARG)
          .setMessage("name=" + name);
    }
  }

  public static TradeType fromName(String name) {
    Optional<TradeType> first = Arrays.asList(values()).stream().filter(type -> type.name.contentEquals(name)).findFirst();
    if (first.isPresent()) {
      return first.get();
    } else {
      return null;
    }
  }
}