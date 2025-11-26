package com.shimo.sdk.common.enums;

import lombok.Getter;

/**
 * 导出文件类型枚举
 */
@Getter
public enum ExportType {
    
    /**
     * PDF 格式
     */
    PDF("pdf"),
    
    /**
     * Word 文档
     */
    DOCX("docx"),
    
    /**
     * Excel 表格
     */
    XLSX("xlsx"),
    
    /**
     * PowerPoint 演示文稿
     */
    PPTX("pptx"),
    
    /**
     * JPG 图片
     */
    JPG("jpg"),
    
    /**
     * PNG 图片
     */
    PNG("png");
    
    private final String value;
    
    ExportType(String value) {
        this.value = value;
    }
}

