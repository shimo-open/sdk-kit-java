package com.shimo.sdk.utils;

import com.shimo.sdk.common.Constants;
import com.shimo.sdk.common.ShimoConfig;
import com.shimo.sdk.common.SdkException;
import com.shimo.sdk.utils.JsonUtil;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 重构后的 HTTP 客户端
 * 支持连接池复用和统一的响应处理
 */
public class HttpClient {

    private final OkHttpClient okHttpClient;
    private final ShimoConfig config;

    /**
     * 构建一个配置化的 HTTP Client
     * @param config SDK 配置
     */
    public HttpClient(ShimoConfig config) {
        this.config = config;
        this.okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(config.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(config.getReadTimeout(), TimeUnit.SECONDS)
                .writeTimeout(config.getWriteTimeout(), TimeUnit.SECONDS)
                .build();
    }

    /**
     * 获取 OkHttpClient 实例
     */
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    /**
     * 构建通用请求头
     */
    private HashMap<String, String> buildHeaders(HashMap<String, String> customHeaders) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(Constants.SIGNATURE, config.getSignature());
        headers.put(Constants.TOKEN, config.getToken());
        
        if (config.getAcceptLanguage() != null && !config.getAcceptLanguage().isEmpty()) {
            headers.put("Accept-Language", config.getAcceptLanguage());
        }
        
        if (customHeaders != null) {
            headers.putAll(customHeaders);
        }
        
        return headers;
    }

    /**
     * 执行请求并统一处理响应
     */
    private Response execute(Request request) throws SdkException {
        try {
            return okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new SdkException("HTTP request failed: " + e.getMessage(), e);
        }
    }

    /**
     * GET 请求
     */
    public Response get(String url, Object body, HashMap<String, String> customHeaders) throws SdkException {
        HashMap<String, String> headers = buildHeaders(customHeaders);
        
        RequestBody requestBody = null;
        if (body != null) {
            MediaType mediaType = MediaType.parse(Constants.JSON_MIME);
            requestBody = RequestBody.create(mediaType, JsonUtil.toJson(body));
        }
        
        Request request = new Request.Builder()
                .url(url)
                .method("GET", requestBody)
                .headers(Headers.of(headers))
                .build();
        
        return execute(request);
    }

    /**
     * POST 请求
     */
    public Response post(String url, Object body, HashMap<String, String> customHeaders) throws SdkException {
        HashMap<String, String> headers = buildHeaders(customHeaders);
        headers.put(Constants.CONTENT_TYPE_HEADER, Constants.JSON_MIME);
        
        MediaType mediaType = MediaType.parse(Constants.JSON_MIME);
        RequestBody requestBody = RequestBody.create(mediaType, JsonUtil.toJson(body));
        
        Request request = new Request.Builder()
                .url(url)
                .method("POST", requestBody)
                .headers(Headers.of(headers))
                .build();
        
        return execute(request);
    }

    /**
     * PUT 请求
     */
    public Response put(String url, Object body, HashMap<String, String> customHeaders) throws SdkException {
        HashMap<String, String> headers = buildHeaders(customHeaders);
        
        RequestBody requestBody = null;
        if (body != null) {
            MediaType mediaType = MediaType.parse(Constants.JSON_MIME);
            requestBody = RequestBody.create(mediaType, JsonUtil.toJson(body));
        }
        
        Request request = new Request.Builder()
                .url(url)
                .method("PUT", requestBody)
                .headers(Headers.of(headers))
                .build();
        
        return execute(request);
    }

    /**
     * DELETE 请求
     */
    public Response delete(String url, Object body, HashMap<String, String> customHeaders) throws SdkException {
        HashMap<String, String> headers = buildHeaders(customHeaders);
        
        RequestBody requestBody = null;
        if (body != null) {
            MediaType mediaType = MediaType.parse(Constants.JSON_MIME);
            requestBody = RequestBody.create(mediaType, JsonUtil.toJson(body));
        }
        
        Request request = new Request.Builder()
                .url(url)
                .method("DELETE", requestBody)
                .headers(Headers.of(headers))
                .build();
        
        return execute(request);
    }

    /**
     * POST Multipart 请求(用于文件上传)
     */
    public Response postMultipart(String url, HashMap<String, Object> formData, HashMap<String, String> customHeaders) throws SdkException {
        HashMap<String, String> headers = buildHeaders(customHeaders);
        
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for (Map.Entry<String, Object> entry : formData.entrySet()) {
            if ("file".equals(entry.getKey())) {
                File file = (File) entry.getValue();
                builder.addFormDataPart(entry.getKey(), file.getName(), 
                    RequestBody.create(MediaType.parse(Constants.STREAM_MIME), file));
            } else {
                builder.addFormDataPart(entry.getKey(), entry.getValue().toString());
            }
        }

        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(Headers.of(headers))
                .build();

        return execute(request);
    }

    /**
     * 关闭客户端,释放资源
     */
    public void shutdown() {
        if (okHttpClient != null) {
            okHttpClient.dispatcher().executorService().shutdown();
            okHttpClient.connectionPool().evictAll();
        }
    }
}

