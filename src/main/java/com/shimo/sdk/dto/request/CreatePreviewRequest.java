package com.shimo.sdk.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import com.shimo.sdk.dto.request.BaseRequest;

/**
 * 创建预览请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreatePreviewRequest extends BaseRequest {
    // 仅需要 fileId,继承自 BaseRequest
}

