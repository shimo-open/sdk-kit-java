package com.shimo.sdk.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class TokenRes {
    private String fileFieldKey;
    private List<FormData> formData;
    private String guid;
    private String images;
    private String key;
    private String serverUrl;
    private String url;
    
    @Data
    public static class FormData {
        private String accessToken;
        private String download;
    }
}
