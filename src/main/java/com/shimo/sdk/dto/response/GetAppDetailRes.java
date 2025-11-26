package com.shimo.sdk.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GetAppDetailRes {
    /**
     * 应用名
     */
    private String appName;

    /**
     * 可用石墨套件列表
     * ["document","documentPro","spreadsheet","presentation","table"]
     */
    private List<String> availableFileTypes;

    /**
     * 允许使用的套件类型
     */
    private List<String> premiumFileTypes;

    /**
     * 已激活席位用户数
     */
    private Integer activatedUserCount;

    /**
     * 用户数总数，包含已激活、已禁用和未使用的用户总数，仅"已激活 "数量占用席位。
     */
    private Integer userCount;

    /**
     * license 席位限制用户数，即"已激活 "用户数量最大限制
     */
    private Integer memberLimit;

    /**
     * license 生效时间
     */
    private String validFrom;

    /**
     * license 有效期截止时间
     */
    private String validUntil;

    /**
     * 接入方回调地址
     */
    private String endpointUrl;
}
