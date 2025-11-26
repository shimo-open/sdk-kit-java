package com.shimo.sdk.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GetTableContentRes {
    private List<List<Object>> values;
    private Integer lag;
}
