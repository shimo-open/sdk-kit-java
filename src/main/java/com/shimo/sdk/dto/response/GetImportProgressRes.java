package com.shimo.sdk.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetImportProgressRes extends BaseResponse {
    private GetImportProgressData data;
    
    @Data
    public static class GetImportProgressData {
        private Integer progress;
    }
}
