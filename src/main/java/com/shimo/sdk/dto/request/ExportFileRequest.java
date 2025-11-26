package com.shimo.sdk.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 导出文件请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExportFileRequest extends BaseRequest {
    
    /**
     * 导出的文件类型
     */
    private String type;
    
    /**
     * 文件格式类型
     */
    private String fileType;
}

