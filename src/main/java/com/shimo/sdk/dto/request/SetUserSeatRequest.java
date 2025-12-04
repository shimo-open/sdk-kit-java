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
     * 需修改席位状态的用户 ID 列表
     */
    private List<String> userIds;

    /**
     * 1激活，0 禁用，-1 未启用
     */
    private Integer status;
}

