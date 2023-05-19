package com.jk.wxpay.v3.commons.bean.combine;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 结算信息。
 */
public class SettleInfo {
    /**
     * 是否指定分账，枚举值
     * true：是
     * false：否
     * 示例值：true
     * [o]
     */
    @JsonProperty("profit_sharing")
    private Boolean profitSharing;

    /**
     * SettleInfo.profit_sharing为true时，该金额才生效。
     * 注意：单笔订单最高补差金额为5000元
     * 示例值：10
     * [o]
     */
    @JsonProperty("subsidy_amount")
    private Long subsidyAmount;

    public SettleInfo() {
    }

    public Boolean getProfitSharing() {
        return profitSharing;
    }

    public SettleInfo setProfitSharing(Boolean profitSharing) {
        this.profitSharing = profitSharing;
        return this;
    }

    public Long getSubsidyAmount() {
        return subsidyAmount;
    }

    public SettleInfo setSubsidyAmount(Long subsidyAmount) {
        this.subsidyAmount = subsidyAmount;
        return this;
    }
}
