package com.shimo.sdk.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 替换书签内容请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReplaceBookmarkContentRequest extends BaseRequest {
    
    /**
     * 书签名称
     */
    private String bookmarkName;
    
    /**
     * 替换的文本内容
     */
    private String replaceText;
}

