package com.jk.wxpay.v3.apache.http;

public class HttpUtils {

    private static final String os = System.getProperty("os.name") + "/" + System.getProperty("os.version");
    private static final String version = System.getProperty("java.version");

    /**
     * 获取UserAgent.
     * @return
     */
    public static String getUserAgent() {
        String userAgent = String.format(
                "wxpay-webflux/%s (%s) Java/%s", HttpUtils.class.getPackage().getImplementationVersion(),
                os,
                version == null ? "Unknown" : version);
        return userAgent;
    }
}
