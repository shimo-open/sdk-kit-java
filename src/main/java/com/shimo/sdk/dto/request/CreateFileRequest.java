package com.shimo.sdk.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 创建文件请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateFileRequest extends BaseRequest {
    
    /**
     * 文件类型
     */
    private String type;
    
    /**
     * 语言设置,默认 zh-CN
     * 可选值: zh-CN(简体中文)、en(英文)、ja(日文)
     */
    private String lang;
    
    /**
     * Accept-Language 请求头
     */
    private String acceptLanguage;
}

