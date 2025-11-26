package com.shimo.sdk.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class TableUploadAttRes {
    private Integer code;
    private Object data;
    private List<ResponseBody> body;
    
    @Data
    public static class ResponseBody {
        private String GUID;
        private String filename;
        private String key;
        private String mimeType;
        private String images;
        private String url;
        private Object audio;
        private Object video;
        private String width;
        private String height;
        private Integer size;
    }
}
