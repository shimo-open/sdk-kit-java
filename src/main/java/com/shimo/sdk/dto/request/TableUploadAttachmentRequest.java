package com.shimo.sdk.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.File;

/**
 * 表格上传附件请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TableUploadAttachmentRequest extends BaseRequest {
    
    /**
     * 要上传的文件
     */
    private File file;
    
    /**
     * 表格范围
     */
    private String range;
}

