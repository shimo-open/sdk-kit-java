package com.shimo.sdk.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 创建文件副本请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateFileCopyRequest extends BaseRequest {
    
    /**
     * 文件类型
     */
    private String type;
    
    /**
     * 目标文件 ID
     */
    private String toFileId;
}

