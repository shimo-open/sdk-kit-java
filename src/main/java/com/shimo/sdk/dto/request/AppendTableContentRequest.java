package com.shimo.sdk.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 追加表格内容请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AppendTableContentRequest extends BaseRequest {
    
    /**
     * 表格范围
     */
    private String range;
    
    /**
     * 要追加的数据内容
     */
    private Object resource;
}

