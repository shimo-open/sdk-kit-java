package com.shimo.sdk.api;

import com.shimo.sdk.ShimoClient;
import com.shimo.sdk.common.Constants;
import com.shimo.sdk.common.SdkException;
import com.shimo.sdk.dto.request.*;
import com.shimo.sdk.dto.response.*;
import com.shimo.sdk.utils.HttpClient;
import com.shimo.sdk.utils.JsonUtil;
import com.shimo.sdk.utils.StrUtil;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

/**
 * 文件操作 API
 * 包含文件的创建、删除、复制、导入、导出等操作
 */
public class FileApi {

    private final ShimoClient client;
    private final HttpClient httpClient;

    public FileApi(ShimoClient client) {
        this.client = client;
        this.httpClient = client.getHttpClient();
    }

    /**
     * 创建协同文档
     */
    public void create(CreateFileRequest request) throws SdkException {
        String url = client.getConfig().getApiPrefix() + "/sdk/v2/api/files";

        HashMap<String, String> headers = new HashMap<>();
        headers.put(Constants.CONTENT_TYPE_HEADER, Constants.JSON_MIME);
        if (StringUtils.isNotBlank(request.getAcceptLanguage())) {
            headers.put("Accept-Language", request.getAcceptLanguage());
        }

        HashMap<String, Object> query = new HashMap<>();
        if (StringUtils.isNotEmpty(request.getLang())) {
            query.put("lang", request.getLang());
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("type", request.getType());
        body.put("fileId", request.getFileId());

        try (Response response = httpClient.post(StrUtil.buildUrlWithQuery(url, query), body, headers)) {
            handleResponse(response);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 创建协同文档副本
     */
    public void createCopy(CreateFileCopyRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/collab-files/%s/copy", request.getFileId());

        HashMap<String, Object> body = new HashMap<>();
        body.put("fileId", request.getToFileId());

        try (Response response = httpClient.post(url, body, null)) {
            handleResponse(response);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 删除协同文档
     */
    public void delete(String fileId) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/%s", fileId);

        try (Response response = httpClient.delete(url, null, null)) {
            handleResponse(response);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 导入文件 (旧)
     */
    public ImportFileRes importFile(ImportFileRequest request) throws SdkException {
        String url = client.getConfig().getApiPrefix() + "/sdk/v2/api/files/v1/import";

        HashMap<String, Object> formData = new HashMap<>();
        formData.put("type", request.getType());
        formData.put("fileId", request.getFileId());
        if (request.getFile() != null) {
            formData.put("file", request.getFile());
        }
        if (StringUtils.isNotEmpty(request.getFileUrl())) {
            formData.put("fileUrl", request.getFileUrl());
        }
        if (StringUtils.isNotEmpty(request.getFileName())) {
            formData.put("fileName", request.getFileName());
        }

        try (Response response = httpClient.postMultipart(url, formData, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), ImportFileRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 导入文件 (新)
     */
    public ImportFileRes importFileNew(ImportFileRequest request) throws SdkException {
        String url = client.getConfig().getApiPrefix() + "/sdk/v2/api/files/v2/import";

        HashMap<String, Object> formData = new HashMap<>();
        formData.put("type", request.getType());
        formData.put("fileId", request.getFileId());
        if (request.getFile() != null) {
            formData.put("file", request.getFile());
        }
        if (StringUtils.isNotEmpty(request.getFileUrl())) {
            formData.put("fileUrl", request.getFileUrl());
        }
        if (StringUtils.isNotEmpty(request.getFileName())) {
            formData.put("fileName", request.getFileName());
        }
        if (StringUtils.isNotEmpty(request.getImportFontType())) {
            formData.put("importFontType", request.getImportFontType());
        }

        try (Response response = httpClient.postMultipart(url, formData, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), ImportFileRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }

    }

    /**
     * 获取导入进度(旧)
     */
    public GetImportProgressRes getImportProgress(GetProgressRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/v1/import/progress?taskId=%s", request.getTaskId());

        try (Response response = httpClient.post(url, null, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), GetImportProgressRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 获取导入进度(新)
     */
    public GetImportProgressRes getImportProgressNew(GetProgressRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/v2/import/progress");

        try (Response response = httpClient.post(url, request, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), GetImportProgressRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 导出文件
     */
    public ExportFileRes exportFile(ExportFileRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/v1/export/%s", request.getFileId());

        HashMap<String, Object> body = new HashMap<>();
        body.put("type", request.getType());
        if (StringUtils.isNotEmpty(request.getFileType())) {
            body.put("fileType", request.getFileType());
        }

        try (Response response = httpClient.post(url, body, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), ExportFileRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 获取导出进度
     */
    public GetExportProgressRes getExportProgress(GetProgressRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/v1/export/progress");

        try (Response response = httpClient.post(url, request, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), GetExportProgressRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 导出应用表格为 Excel(旧)
     */
    public ExportTableSheetsRes exportTableSheets(String fileId) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/files/export/table-sheets/%s", fileId);

        try (Response response = httpClient.post(url, null, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), ExportTableSheetsRes.class);
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
