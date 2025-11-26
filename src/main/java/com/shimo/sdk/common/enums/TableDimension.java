package com.shimo.sdk.common.enums;

import lombok.Getter;

/**
 * 表格维度枚举(用于删除操作)
 */
@Getter
public enum TableDimension {
    
    /**
     * 行
     */
    ROWS("ROWS"),
    
    /**
     * 列
     */
    COLUMNS("COLUMNS");
    
    private final String value;
    
    TableDimension(String value) {
        this.value = value;
    }
}

