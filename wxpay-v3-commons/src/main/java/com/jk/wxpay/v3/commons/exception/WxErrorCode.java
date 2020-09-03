package com.jk.wxpay.v3.commons.exception;

public interface WxErrorCode {
    /**
     * 这些错误码为系统内部使用。
     */
    String NO_SUCH_ALGORITHM = "NoSuchAlgorithm";
    String INVALID_KEY = "InvalidKey";
    String SIGNATURE_EXCEPTION = "SignatureException";
    String ILLEGAL_ARG = "IllegalArg";
    String WX_RESPONSE_INVALID = "WxResponseInvalid";
    String NOT_SUPPORTED_TYPE = "NotSupportedType";


    /**
     * 如下定义微信返回的错误码
     *
     */
    String USERPAYING = "USERPAYING";  // 用户支付中，需要输入密码	等待5秒，然后调用被扫订单结果查询API，查询当前订单的不同状态，决定下一步的操作
    String TRADE_ERROR = "TRADE_ERROR"; // 交易错误	因业务原因交易失败，请查看接口返回的详细信息
    String SYSTEMERROR = "SYSTEMERROR"; // 系统错误	系统异常，请用相同参数重新调用
    String SIGN_ERROR = "SIGN_ERROR"; // 签名错误	请检查签名参数和方法是否都符合签名算法要求
    String RULELIMIT = "RULELIMIT";  // 业务规则限制	因业务规则限制请求频率，请查看接口返回的详细信息
    String PARAM_ERROR = "PARAM_ERROR"; // 参数错误	请根据接口返回的详细信息检查请求参数
    String OUT_TRADE_NO_USED = "OUT_TRADE_NO_USED"; // 商户订单号重复	请核实商户订单号是否重复提交
    String ORDERNOTEXIST = "ORDERNOTEXIST"; // 订单不存在	请检查订单是否发起过交易
    String ORDER_CLOSED = "ORDER_CLOSED";  // 订单已关闭	当前订单已关闭，请重新下单
    String OPENID_MISMATCH = "OPENID_MISMATCH";  // openid和appid不匹配	请确认openid和appid是否匹配
    String NOTENOUGH = "NOTENOUGH";  // 余额不足	用户帐号余额不足，请用户充值或更换支付卡后再支付
    String NOAUTH = "NOAUTH"; // 商户无权限	请商户前往申请此接口相关权限
    String MCH_NOT_EXISTS = "MCH_NOT_EXISTS"; // 商户号不存在	请检查商户号是否正确
    String INVALID_TRANSACTIONID = "INVALID_TRANSACTIONID"; // 订单号非法	请检查微信支付订单号是否正确
    String INVALID_REQUEST = "INVALID_REQUEST";  // 无效请求	请根据接口返回的详细信息检查
    String FREQUENCY_LIMITED = "FREQUENCY_LIMITED"; // 频率超限	请降低请求接口频率
    String BANKERROR = "BANKERROR";  // 银行系统异常	银行系统异常，请用相同参数重新调用
    String APPID_MCHID_NOT_MATCH = "APPID_MCHID_NOT_MATCH";  //appid和mch_id不匹配	请确认appid和mch_id是否匹配
    String ACCOUNTERROR = "ACCOUNTERROR";   //账号异常	用户账号异常，无需更多操作

}
