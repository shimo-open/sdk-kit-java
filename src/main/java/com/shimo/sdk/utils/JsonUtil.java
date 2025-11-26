package com.shimo.sdk.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final JsonParser JSON_PARSER = new JsonParser();

    /**
     * 将对象转换为 JSON 字符串
     *
     * @param obj 要转换的对象
     * @return JSON 字符串
     */
    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    /**
     * 将 JSON 字符串转换为指定类型的对象
     *
     * @param json JSON 字符串
     * @param clazz 目标类
     * @param <T> 泛型类型
     * @return 转换后的对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    /**
     * 将 JSON 字符串转换为指定类型的对象（使用 TypeToken）
     *
     * @param json JSON 字符串
     * @param type 泛型类型
     * @param <T> 泛型类型
     * @return 转换后的对象
     */
    public static <T> T fromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    /**
     * 将 JSON 字符串转换为 List
     *
     * @param json JSON 字符串
     * @param clazz 列表元素的类
     * @param <T> 泛型类型
     * @return 转换后的 List
     */
    public static <T> List<T> fromJsonToList(String json, Class<T> clazz) {
        Type listType = new TypeToken<List<T>>() {}.getType();
        return GSON.fromJson(json, listType);
    }

    /**
     * 将 JSON 字符串转换为 Map
     *
     * @param json JSON 字符串
     * @param keyClass Map 键的类
     * @param valueClass Map 值的类
     * @param <K> 键的泛型类型
     * @param <V> 值的泛型类型
     * @return 转换后的 Map
     */
    public static <K, V> Map<K, V> fromJsonToMap(String json, Class<K> keyClass, Class<V> valueClass) {
        Type mapType = new TypeToken<Map<K, V>>() {}.getType();
        return GSON.fromJson(json, mapType);
    }

    /**
     * 将 JSON 字符串转换为 JsonObject
     *
     * @param json JSON 字符串
     * @return JsonObject
     */
    public static JsonObject parseJsonObject(String json) {
        return JSON_PARSER.parse(json).getAsJsonObject();
    }

    /**
     * 将 JSON 字符串转换为 JsonArray
     *
     * @param json JSON 字符串
     * @return JsonArray
     */
    public static JsonArray parseJsonArray(String json) {
        return JSON_PARSER.parse(json).getAsJsonArray();
    }

    /**
     * 将 JSON 字符串转换为 JsonElement
     *
     * @param json JSON 字符串
     * @return JsonElement
     */
    public static JsonElement parseJsonElement(String json) {
        return JSON_PARSER.parse(json);
    }

    /**
     * 检查字符串是否是有效的 JSON
     *
     * @param json JSON 字符串
     * @return 是否是有效的 JSON
     */
    public static boolean isValidJson(String json) {
        try {
            JSON_PARSER.parse(json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 将 JsonObject 转换为 Map
     *
     * @param jsonObject JsonObject
     * @return Map
     */
    public static Map<String, JsonElement> toMap(JsonObject jsonObject) {
        return GSON.fromJson(jsonObject, new TypeToken<Map<String, JsonElement>>() {}.getType());
    }

    /**
     * 将 Map 转换为 JsonObject
     *
     * @param map Map
     * @return JsonObject
     */
    public static JsonObject toJsonObject(Map<String, ? extends JsonElement> map) {
        return GSON.toJsonTree(map).getAsJsonObject();
    }

    /**
     * 将 List 转换为 JsonArray
     *
     * @param list List
     * @return JsonArray
     */
    public static JsonArray toJsonArray(List<? extends JsonElement> list) {
        return GSON.toJsonTree(list).getAsJsonArray();
    }

    /**
     * 将 JsonArray 转换为 List
     *
     * @param jsonArray JsonArray
     * @return List
     */
    public static List<JsonElement> toList(JsonArray jsonArray) {
        return GSON.fromJson(jsonArray, new TypeToken<List<JsonElement>>() {}.getType());
    }
}
