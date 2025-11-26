package com.shimo.sdk.dto.response;

import lombok.Data;

@Data
public class GetRevisionRes {
    private String id;
    private String label;
    private String title;
    private String docHistoryId;
    private String createdAt;
    private String updatedAt;
    
    @Data
    public static class User {
        private String id;
        private String name;
    }
}
