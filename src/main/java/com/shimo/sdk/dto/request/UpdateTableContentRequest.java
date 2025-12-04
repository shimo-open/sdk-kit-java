package com.shimo.sdk.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 更新表格内容请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateTableContentRequest extends BaseRequest {
    
    /**
     * 表格范围,例如: Sheet1!A1:C10
     */
    private String range;
    
    /**
     * 要更新的数据内容
     */
    private Resource resource;

    @Data
    @Builder
    public static class Resource {
        /**
         * 表格内容
         */
        private List<List<String>> values;
    }
}

