package com.shimo.sdk.common.enums;

import lombok.Getter;

/**
 * 文件类型枚举
 */
@Getter
public enum FileType {
    
    /**
     * 轻文档
     */
    DOCUMENT("document"),
    
    /**
     * 表格(Excel)
     */
    SPREADSHEET("spreadsheet"),
    
    /**
     * 传统文档(Word)
     */
    DOCUMENT_PRO("documentPro"),
    
    /**
     * 幻灯片(PPT)
     */
    PRESENTATION("presentation"),
    
    /**
     * 应用表格
     */
    TABLE("table");
    
    private final String value;
    
    FileType(String value) {
        this.value = value;
    }
    
    /**
     * 根据字符串值获取文件类型
     */
    public static FileType fromValue(String value) {
        for (FileType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown file type: " + value);
    }
}

