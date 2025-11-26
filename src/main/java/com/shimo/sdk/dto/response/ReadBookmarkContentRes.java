package com.shimo.sdk.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ReadBookmarkContentRes {
    private List<ReadBookmarkContentData> data;

    @Data
    public static class ReadBookmarkContentData {
        private String bookmark;
        private String content;
    }
}
