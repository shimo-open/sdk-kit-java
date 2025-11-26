package com.shimo.sdk;

import com.shimo.sdk.api.DocumentApi;
import com.shimo.sdk.api.FileApi;
import com.shimo.sdk.api.PreviewApi;
import com.shimo.sdk.api.SystemApi;
import com.shimo.sdk.api.TableApi;
import com.shimo.sdk.common.ShimoConfig;
import com.shimo.sdk.common.SdkException;
import com.shimo.sdk.utils.HttpClient;

/**
 * Shimo SDK 主客户端
 * 所有 API 操作的入口
 */
public class ShimoClient {

    private final ShimoConfig config;
    private final HttpClient httpClient;
    
    // API 模块实例(懒加载)
    private FileApi fileApi;
    private TableApi tableApi;
    private DocumentApi documentApi;
    private PreviewApi previewApi;
    private SystemApi systemApi;

    /**
     * 使用配置创建客户端
     * @param config SDK 配置
     * @throws SdkException 如果配置无效
     */
    public ShimoClient(ShimoConfig config) throws SdkException {
        if (config == null) {
            throw new SdkException("ShimoConfig cannot be null");
        }
        config.validate();
        
        this.config = config;
        this.httpClient = new HttpClient(config);
    }

    /**
     * 快速创建客户端(使用默认配置)
     * @param signature 签名
     * @param token 令牌
     * @throws SdkException 如果参数无效
     */
    public ShimoClient(String signature, String token) throws SdkException {
        this(ShimoConfig.builder()
                .signature(signature)
                .token(token)
                .build());
    }

    /**
     * 获取文件操作 API
     */
    public FileApi files() {
        if (fileApi == null) {
            fileApi = new FileApi(this);
        }
        return fileApi;
    }

    /**
     * 获取表格操作 API
     */
    public TableApi tables() {
        if (tableApi == null) {
            tableApi = new TableApi(this);
        }
        return tableApi;
    }

    /**
     * 获取文档操作 API
     */
    public DocumentApi documents() {
        if (documentApi == null) {
            documentApi = new DocumentApi(this);
        }
        return documentApi;
    }

    /**
     * 获取预览操作 API
     */
    public PreviewApi preview() {
        if (previewApi == null) {
            previewApi = new PreviewApi(this);
        }
        return previewApi;
    }

    /**
     * 获取系统操作 API
     */
    public SystemApi system() {
        if (systemApi == null) {
            systemApi = new SystemApi(this);
        }
        return systemApi;
    }

    /**
     * 获取 HTTP 客户端(供 API 模块使用)
     */
    public HttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * 获取配置(供 API 模块使用)
     */
    public ShimoConfig getConfig() {
        return config;
    }

    /**
     * 关闭客户端,释放资源
     */
    public void close() {
        if (httpClient != null) {
            httpClient.shutdown();
        }
    }
}

