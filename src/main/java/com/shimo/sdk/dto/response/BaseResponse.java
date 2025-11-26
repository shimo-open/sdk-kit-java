package com.shimo.sdk.dto.response;

import lombok.Data;

/**
 * 基础响应类
 * 包含通用的响应字段
 */
@Data
public abstract class BaseResponse {
    
    /**
     * 状态码
     */
    protected Integer status;
    
    /**
     * 响应消息
     */
    protected String message;
}

