package com.shimo.sdk.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 设置用户席位请求
 */
@Data
@Builder
public class SetUserSeatRequest {
    
    /**
     * 单个用户 ID(用于激活/取消单个用户席位)
     */
    private String userId;
    
    /**
     * 要激活席位的用户 ID 列表(用于批量设置)
     */
    private List<String> activateUsers;
    
    /**
     * 要取消席位的用户 ID 列表(用于批量设置)
     */
    private List<String> deactivateUsers;
}

