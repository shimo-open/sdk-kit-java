package com.shimo.sdk.api;

import com.shimo.sdk.ShimoClient;
import com.shimo.sdk.common.SdkException;
import com.shimo.sdk.dto.request.GetHistoryRequest;
import com.shimo.sdk.dto.request.GetPlainTextWordCountRequest;
import com.shimo.sdk.dto.request.ReadBookmarkContentRequest;
import com.shimo.sdk.dto.request.ReplaceBookmarkContentRequest;
import com.shimo.sdk.dto.response.GetCommentCountRes;
import com.shimo.sdk.dto.response.GetHistoryRes;
import com.shimo.sdk.dto.response.GetMentionAtRes;
import com.shimo.sdk.dto.response.GetPlainTextRes;
import com.shimo.sdk.dto.response.GetPlainTextWCRes;
import com.shimo.sdk.dto.response.GetRevisionRes;
import com.shimo.sdk.dto.response.ReadBookmarkContentRes;
import com.shimo.sdk.utils.HttpClient;
import com.shimo.sdk.utils.JsonUtil;
import okhttp3.Response;

import java.util.HashMap;
import java.util.List;

/**
 * 文档操作 API
 * 包含文档的历史版本、纯文本、书签、评论、@提及等操作
 */
public class DocumentApi {

    private final ShimoClient client;
    private final HttpClient httpClient;

    public DocumentApi(ShimoClient client) {
        this.client = client;
        this.httpClient = client.getHttpClient();
    }

    /**
     * 获取历史版本
     */
    public GetHistoryRes getHistory(GetHistoryRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/histories", request.getFileId());

        HashMap<String, Object> body = new HashMap<>();
        body.put("page", request.getPage());
        body.put("pageSize", request.getPageSize());

        try (Response response = httpClient.get(url, body, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), GetHistoryRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 获取版本列表
     */
    public List<GetRevisionRes> getRevision(String fileId) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/revisions", fileId);

        try (Response response = httpClient.get(url, null, null)) {
            handleResponse(response);
            return JsonUtil.fromJsonToList(response.body().string(), GetRevisionRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 获取文件纯文本内容
     */
    public GetPlainTextRes getPlainText(String fileId) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/plain-text", fileId);

        try (Response response = httpClient.get(url, null, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), GetPlainTextRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 文件纯文本字数统计
     */
    public GetPlainTextWCRes getPlainTextWordCount(GetPlainTextWordCountRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/word-count", request.getFileId());

        HashMap<String, Object> body = new HashMap<>();
        body.put("type", request.getType());

        try (Response response = httpClient.post(url, body, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), GetPlainTextWCRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 获取文件内容中所有的 @ 人信息列表
     */
    public GetMentionAtRes getMentionAt(String fileId) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/mention/at", fileId);

        try (Response response = httpClient.get(url, null, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), GetMentionAtRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 获取文件中的评论数
     */
    public GetCommentCountRes getCommentCount(String fileId) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/comment-count", fileId);

        try (Response response = httpClient.get(url, null, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), GetCommentCountRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 读取传统文档书签内容
     */
    public ReadBookmarkContentRes readBookmarkContent(ReadBookmarkContentRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/read-bookmark", request.getFileId());

        HashMap<String, Object> body = new HashMap<>();
        body.put("bookmarkName", request.getBookmarkName());

        try (Response response = httpClient.post(url, body, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), ReadBookmarkContentRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 替换传统文档书签内容
     */
    public void replaceBookmarkContent(ReplaceBookmarkContentRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/replace-bookmark", request.getFileId());

        HashMap<String, Object> body = new HashMap<>();
        body.put("bookmarkName", request.getBookmarkName());
        body.put("replaceText", request.getReplaceText());

        try (Response response = httpClient.post(url, body, null)) {
            handleResponse(response);
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
