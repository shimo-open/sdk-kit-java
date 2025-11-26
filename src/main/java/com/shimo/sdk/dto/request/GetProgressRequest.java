package com.shimo.sdk.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * 获取进度请求(导入/导出)
 * 此类不继承 BaseRequest,因为使用 taskId 而非 fileId
 */
@Data
@Builder
public class GetProgressRequest {
    
    /**
     * 导入/导出接口返回的任务 ID
     */
    private String taskId;
}

