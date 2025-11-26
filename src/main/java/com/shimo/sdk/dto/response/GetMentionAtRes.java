package com.shimo.sdk.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GetMentionAtRes {
    private List<MentionAt> mentionAtList;
    
    @Data
    public static class MentionAt {
        private String userId;
        private String atGuid;
    }
}
