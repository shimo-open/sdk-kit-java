package com.shimo.sdk.api;

import com.shimo.sdk.ShimoClient;
import com.shimo.sdk.common.SdkException;
import com.shimo.sdk.dto.request.AddTableSheetRequest;
import com.shimo.sdk.dto.request.AppendTableContentRequest;
import com.shimo.sdk.dto.request.DeleteTableRowRequest;
import com.shimo.sdk.dto.request.GetTableContentRequest;
import com.shimo.sdk.dto.request.TableUploadAttachmentRequest;
import com.shimo.sdk.dto.request.UpdateTableContentRequest;
import com.shimo.sdk.dto.response.GetTableContentRes;
import com.shimo.sdk.dto.response.TableUploadAttRes;
import com.shimo.sdk.utils.HttpClient;
import com.shimo.sdk.utils.JsonUtil;
import okhttp3.Response;

import java.util.HashMap;

/**
 * 表格操作 API
 * 包含表格内容的读写、行操作、工作表管理、附件上传等
 */
public class TableApi {

    private final ShimoClient client;
    private final HttpClient httpClient;

    public TableApi(ShimoClient client) {
        this.client = client;
        this.httpClient = client.getHttpClient();
    }

    /**
     * 获取表格内容
     */
    public GetTableContentRes getContent(GetTableContentRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/sheets/values", request.getFileId());

        HashMap<String, Object> body = new HashMap<>();
        body.put("range", request.getRange());

        try (Response response = httpClient.get(url, body, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), GetTableContentRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 更新表格内容
     */
    public void updateContent(UpdateTableContentRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/sheets/values", request.getFileId());

        HashMap<String, Object> body = new HashMap<>();
        body.put("range", request.getRange());
        body.put("resource", request.getResource());

        try (Response response = httpClient.post(url, body, null)) {
            handleResponse(response);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 追加表格内容
     */
    public void appendContent(AppendTableContentRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/sheets/values/append", request.getFileId());

        HashMap<String, Object> body = new HashMap<>();
        body.put("range", request.getRange());
        body.put("resource", request.getResource());

        try (Response response = httpClient.post(url, body, null)) {
            handleResponse(response);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 删除表格行
     */
    public void deleteRow(DeleteTableRowRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/sheets/deleteRange", request.getFileId());

        HashMap<String, Object> body = new HashMap<>();
        body.put("range", request.getRange());
        body.put("dimension", request.getDimension());

        try (Response response = httpClient.post(url, body, null)) {
            handleResponse(response);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 新增表格工作表
     */
    public void addSheet(AddTableSheetRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/sheets/add", request.getFileId());

        HashMap<String, Object> body = new HashMap<>();
        body.put("name", request.getName());

        try (Response response = httpClient.post(url, body, null)) {
            handleResponse(response);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 表格上传附件
     */
    public TableUploadAttRes uploadAttachment(TableUploadAttachmentRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s/attach", request.getFileId());

        HashMap<String, Object> formData = new HashMap<>();
        formData.put("file", request.getFile());
        formData.put("range", request.getRange());

        try (Response response = httpClient.postMultipart(url, formData, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), TableUploadAttRes.class);
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
