package com.shimo.sdk.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 替换书签内容请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReplaceBookmarkContentRequest extends BaseRequest {

    private List<Replacement> replacements;

    @Data
    @Builder
    public static class Replacement {

        private String bookmark;

        private String type;

        private String value;
    }
}

