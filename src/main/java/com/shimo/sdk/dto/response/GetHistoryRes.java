package com.shimo.sdk.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GetHistoryRes {
    private List<History> histories;
    private boolean isLastPage;
    private Integer limit;
    private Object users;
    
    @Data
    public static class History {
        private String content;
        private String createdAt;
        private Integer historyType;
        private String id;
        private String name;
        private String updatedAt;
        private String userId;
    }
}
