package com.shimo.sdk.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ExportFileRes extends BaseResponse {
    private ExportFileData data;

    @Data
    public static class ExportFileData {
        private String taskId;
    }
}
