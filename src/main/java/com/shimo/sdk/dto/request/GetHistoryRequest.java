package com.shimo.sdk.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 获取历史版本请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetHistoryRequest extends BaseRequest {
    
    /**
     * 页码
     */
    private Integer count;
    
    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 操作历史
     */
    private Integer historyType;
}

