package com.shimo.sdk.api;

import com.shimo.sdk.ShimoClient;
import com.shimo.sdk.common.SdkException;
import com.shimo.sdk.dto.request.GetAppDetailRequest;
import com.shimo.sdk.dto.request.GetUserStatusRequest;
import com.shimo.sdk.dto.request.SetUserSeatRequest;
import com.shimo.sdk.dto.request.UpdateCallbackUrlRequest;
import com.shimo.sdk.dto.response.GetAppDetailRes;
import com.shimo.sdk.dto.response.GetUserStatusRes;
import com.shimo.sdk.utils.HttpClient;
import com.shimo.sdk.utils.JsonUtil;
import okhttp3.Response;

import java.util.HashMap;
import java.util.List;

/**
 * 系统操作 API
 * 包含应用信息、席位管理、回调地址等系统级操作
 */
public class SystemApi {

    private final ShimoClient client;
    private final HttpClient httpClient;

    public SystemApi(ShimoClient client) {
        this.client = client;
        this.httpClient = client.getHttpClient();
    }

    /**
     * 获取应用详情
     */
    public GetAppDetailRes getAppDetail(GetAppDetailRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/license/apps/%s", request.getAppId());

        try (Response response = httpClient.get(url, null, null)) {
            handleResponse(response);
            return JsonUtil.fromJson(response.body().string(), GetAppDetailRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 更新应用回调地址
     */
    public void updateCallbackUrl(UpdateCallbackUrlRequest request) throws SdkException {
        String url = String.format(client.getConfig().getApiPrefix() + "/sdk/v2/api/license/apps/%s/callback", request.getAppId());

        HashMap<String, Object> body = new HashMap<>();
        body.put("url", request.getUrl());

        try (Response response = httpClient.put(url, body, null)) {
            handleResponse(response);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 获取用户列表和席位状态
     */
    public List<GetUserStatusRes> getUserStatus(GetUserStatusRequest request) throws SdkException {
        String url = client.getConfig().getApiPrefix() + "/sdk/v2/api/license/users/status";

        HashMap<String, Object> body = new HashMap<>();
        body.put("userIds", request.getUserIds());

        try (Response response = httpClient.post(url, body, null)) {
            handleResponse(response);
            return JsonUtil.fromJsonToList(response.body().string(), GetUserStatusRes.class);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 激活用户席位
     */
    public void activateUserSeat(SetUserSeatRequest request) throws SdkException {
        String url = client.getConfig().getApiPrefix() + "/sdk/v2/api/license/users/activate";

        HashMap<String, Object> body = new HashMap<>();
        body.put("userId", request.getUserId());

        try (Response response = httpClient.post(url, body, null)) {
            handleResponse(response);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 取消用户席位
     */
    public void cancelUserSeat(SetUserSeatRequest request) throws SdkException {
        String url = client.getConfig().getApiPrefix() + "/sdk/v2/api/license/users/deactivate";

        HashMap<String, Object> body = new HashMap<>();
        body.put("userId", request.getUserId());

        try (Response response = httpClient.post(url, body, null)) {
            handleResponse(response);
        } catch (Exception e) {
            throw new SdkException(e);
        }
    }

    /**
     * 批量设置用户席位
     */
    public void batchSetUserSeat(SetUserSeatRequest request) throws SdkException {
        String url = client.getConfig().getApiPrefix() + "/sdk/v2/api/license/users/batch-set";

        HashMap<String, Object> body = new HashMap<>();
        body.put("activateUsers", request.getActivateUsers());
        body.put("deactivateUsers", request.getDeactivateUsers());

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
