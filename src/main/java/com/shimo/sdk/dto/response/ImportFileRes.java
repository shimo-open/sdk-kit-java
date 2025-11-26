package com.shimo.sdk.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ImportFileRes extends BaseResponse {
    private ImportFileData data;

    @Data
    public static class ImportFileData {
        private String taskId;
    }
}
