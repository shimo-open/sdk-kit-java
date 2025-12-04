package com.shimo.sdk.api;

import com.shimo.sdk.ShimoClient;
import com.shimo.sdk.common.SdkException;
import com.shimo.sdk.dto.request.GetHistoryRequest;
import com.shimo.sdk.dto.request.GetPlainTextWordCountRequest;
import com.shimo.sdk.dto.request.ReadBookmarkContentRequest;
import com.shimo.sdk.dto.request.ReplaceBookmarkContentRequest;
import com.shimo.sdk.dto.response.*;
import com.shimo.sdk.utils.HttpClient;
import com.shimo.sdk.utils.JsonUtil;
import okhttp3.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/collab-files/%s/doc-sidebar-info", request.getFileId());

        Map<String, String> params = new HashMap<>();
        if (request.getCount() != null) {
            params.put("count", request.getCount().toString());
        }
        if (request.getPageSize() != null) {
            params.put("pageSize", request.getPageSize().toString());
        }
        if (request.getHistoryType() != null) {
            params.put("historyType", request.getHistoryType().toString());
        }

        StringBuilder sb = new StringBuilder(url);

        if (!params.isEmpty()) {
            sb.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        }


        try (Response response = httpClient.get(sb.toString(), null)) {
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
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/collab-files/%s/revisions", fileId);

        try (Response response = httpClient.get(url, null)) {
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
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/collab-files/%s/plain-text", fileId);

        try (Response response = httpClient.get(url, null)) {
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
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/collab-files/%s/plain-text/wc", request.getFileId());

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
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/collab-files/%s/mention-at-list", fileId);

        try (Response response = httpClient.get(url, null)) {
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
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/collab-files/%s/comment-count", fileId);

        try (Response response = httpClient.get(url, null)) {
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
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/documentpro/bookmark_content", request.getFileId());

        StringBuilder sb = new StringBuilder(url);
        if (request.getBookmarks() != null && !request.getBookmarks().isEmpty()) {
            sb.append("?");
            for (String bookmark : request.getBookmarks()) {
                sb.append("bookmarks=").append(bookmark).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        try (Response response = httpClient.get(sb.toString(), null)) {
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
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/documentpro/bookmark_content", request.getFileId());

        try (Response response = httpClient.put(url, request, null)) {
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
