package com.shimo.sdk.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 删除表格行请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DeleteTableRowRequest extends BaseRequest {
    
    /**
     * 表格范围
     */
    private String range;
    
    /**
     * 维度: ROWS(行) 或 COLUMNS(列)
     */
    private String dimension;
}

