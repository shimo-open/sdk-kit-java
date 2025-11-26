package com.shimo.sdk.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 访问预览请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccessPreviewRequest extends BaseRequest {
    
    /**
     * 预览 ID
     */
    private String previewId;
    
    /**
     * 语言设置,默认 zh-CN
     */
    private String lang;
}

