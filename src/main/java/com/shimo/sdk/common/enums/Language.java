package com.shimo.sdk.common.enums;

import lombok.Getter;

/**
 * 语言枚举
 */
@Getter
public enum Language {
    
    /**
     * 简体中文
     */
    ZH_CN("zh-CN"),
    
    /**
     * 英文
     */
    EN("en"),
    
    /**
     * 英文(美国)
     */
    EN_US("en-US"),
    
    /**
     * 日文
     */
    JA("ja"),
    
    /**
     * 日文(日本)
     */
    JA_JP("ja-JP");
    
    private final String value;
    
    Language(String value) {
        this.value = value;
    }
}

