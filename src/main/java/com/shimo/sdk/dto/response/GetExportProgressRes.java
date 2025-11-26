package com.shimo.sdk.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetExportProgressRes extends BaseResponse {
    private GetExportProgressData data;
    
    @Data
    public static class GetExportProgressData {
        private Integer progress;
        private String downloadUrl;
    }
}
