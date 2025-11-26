package com.shimo.sdk.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * 更新回调地址请求
 */
@Data
@Builder
public class UpdateCallbackUrlRequest {
    
    /**
     * 应用 ID
     */
    private String appId;
    
    /**
     * 回调 URL
     */
    private String url;
}

