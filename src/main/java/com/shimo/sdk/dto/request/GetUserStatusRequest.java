package com.shimo.sdk.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * 获取用户状态请求
 */
@Data
@Builder
public class GetUserStatusRequest {

    private Integer page;

    private Integer size;
}

