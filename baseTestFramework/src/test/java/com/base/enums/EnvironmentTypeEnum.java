package com.base.enums;

/**
 * Description: 根据不同环境来选择 url 地址的枚举类
 * Created by cy on 2025-02-08 13:20
 * Created with IntelliJ IDEA.
 */
public enum EnvironmentTypeEnum {

    PROD("https://www.prod.com"),
    TEST("https://chat.deepseek.com/sign_in"),
    DEV("https://www.dev.com");

    private final String url;

    EnvironmentTypeEnum(String url) {
        this.url = url;
    }

    /**
     * 获取 url
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * 通过枚举名获取 url
     * @param type
     * @return
     */
    public static String getUrlByType(EnvironmentTypeEnum type) {
        return type.getUrl();
    }
}
