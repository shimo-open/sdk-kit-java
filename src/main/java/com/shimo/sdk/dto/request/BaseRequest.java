package com.shimo.sdk.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * 基础请求类
 * 所有请求类的父类,包含通用字段
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseRequest {
    
    /**
     * 文件 ID (大部分接口都需要)
     */
    protected String fileId;
    
    // 注意: signature 和 token 已由 ShimoClient 管理,不再需要在请求中传递
}

