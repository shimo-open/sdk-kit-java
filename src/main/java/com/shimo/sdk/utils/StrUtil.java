package com.shimo.sdk.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class StrUtil {

    public static String buildUrlWithQuery(String url, HashMap<String, Object> query) {
        StringBuilder urlBuilder = new StringBuilder(url);
        if (!query.isEmpty()) {
            urlBuilder.append("?");
            boolean first = true;
            for (Map.Entry<String, Object> entry : query.entrySet()) {
                if (!first) {
                    urlBuilder.append("&");
                }
                urlBuilder.append(entry.getKey())
                        .append("=")
                        .append(encodeValue(entry.getValue().toString()));
                first = false;
            }
        }
        return urlBuilder.toString();
    }
    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 should always be supported", e);
        }
    }
}
