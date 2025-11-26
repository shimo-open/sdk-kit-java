package com.shimo.sdk.common;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * 公用api接收类
 */
@Data
@Builder
public class ApiConf {
    private String url;
    private String method;
    private HashMap<String, Object> headers;
    private HashMap<String, Object> query;
    private HashMap<String, Object> body;
    private HashMap<String, Object> formData;
}
