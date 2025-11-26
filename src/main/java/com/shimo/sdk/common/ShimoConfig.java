package com.shimo.sdk.common;

import lombok.Builder;
import lombok.Data;

/**
 * Shimo SDK 配置类
 */
@Data
@Builder
public class ShimoConfig {
    
    /**
     * 签名,用于验证请求合法性
     */
    private final String signature;
    
    /**
     * 访问令牌
     */
    private final String token;
    
    /**
     * API 前缀地址,默认使用 Constants.PREFIX
     */
    @Builder.Default
    private final String apiPrefix = Constants.PREFIX;
    
    /**
     * 连接超时时间(秒),默认 30 秒
     */
    @Builder.Default
    private final int connectTimeout = Constants.CONNECT_TIMEOUT;
    
    /**
     * 读超时时间(秒),默认 60 秒
     */
    @Builder.Default
    private final int readTimeout = Constants.READ_TIMEOUT;
    
    /**
     * 写超时时间(秒),默认 60 秒
     */
    @Builder.Default
    private final int writeTimeout = Constants.WRITE_TIMEOUT;
    
    /**
     * 语言设置
     */
    private final String acceptLanguage;
    
    /**
     * 验证配置的有效性
     */
    public void validate() throws SdkException {
        if (signature == null || signature.trim().isEmpty()) {
            throw new SdkException("Signature cannot be null or empty");
        }
        if (token == null || token.trim().isEmpty()) {
            throw new SdkException("Token cannot be null or empty");
        }
    }
    
    /**
     * 创建默认配置构建器
     */
    public static ShimoConfigBuilder builder() {
        return new ShimoConfigBuilder();
    }
}

