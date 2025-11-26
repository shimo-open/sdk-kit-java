package com.shimo.sdk.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * 获取应用详情请求
 * 此类不继承 BaseRequest,因为使用 appId 而非 fileId
 */
@Data
@Builder
public class GetAppDetailRequest {
    
    /**
     * 应用 ID
     */
    private String appId;
}

