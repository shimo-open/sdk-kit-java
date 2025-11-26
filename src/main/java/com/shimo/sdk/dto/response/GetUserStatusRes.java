package com.shimo.sdk.dto.response;

import lombok.Data;

@Data
public class GetUserStatusRes {
    private String userId;
    private String createdAt;
    private Integer status;
}
