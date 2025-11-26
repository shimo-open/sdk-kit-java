package com.shimo.sdk.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 获取用户状态请求
 */
@Data
@Builder
public class GetUserStatusRequest {
    
    /**
     * 用户 ID 列表
     */
    private List<String> userIds;
}

