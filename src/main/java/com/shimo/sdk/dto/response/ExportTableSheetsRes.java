package com.shimo.sdk.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ExportTableSheetsRes extends BaseResponse {
    private String downloadUrl;
}
