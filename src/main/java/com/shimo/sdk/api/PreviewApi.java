package com.shimo.sdk.api;

import com.shimo.sdk.ShimoClient;
import com.shimo.sdk.common.SdkException;
import com.shimo.sdk.dto.request.AccessPreviewRequest;
import com.shimo.sdk.dto.request.CreatePreviewRequest;
import com.shimo.sdk.dto.response.CreatePreviewRes;
import com.shimo.sdk.utils.HttpClient;
import com.shimo.sdk.utils.JsonUtil;
import okhttp3.Response;

import java.util.HashMap;

/**
 * 预览操作 API
 * 包含创建预览和访问预览等操作
 */
public class PreviewApi {

    private final ShimoClient client;
    private final HttpClient httpClient;

    public PreviewApi(ShimoClient client) {
        this.client = client;
        this.httpClient = client.getHttpClient();
    }

    /**
     * 创建预览
     */
    public CreatePreviewRes create(CreatePreviewRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/preview", request.getFileId());

        try (Response response = httpClient.post(url, new HashMap<>(), null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), CreatePreviewRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 访问预览
     */
    public String access(AccessPreviewRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/preview/%s", 
            request.getFileId(), request.getPreviewId());

        try (Response response = httpClient.get(url, null, null)) {
            handleResponse(response);
            return response.body().string();
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 统一处理响应
     */
    private void handleResponse(Response response) throws SdkException {
        if (!response.isSuccessful()) {
            try {
                if (response.body() != null) {
                    throw new SdkException(response.code(), response.body().string());
                }
                throw new SdkException(response.code(), response.toString());
            } catch (Exception e) {
                throw new SdkException(response.code(), "Request failed: " + e.getMessage());
            }
        }
    }
}
