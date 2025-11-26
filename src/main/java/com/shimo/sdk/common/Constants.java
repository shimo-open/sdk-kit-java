package com.shimo.sdk.common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class Constants {

    /**
     * 连接超时时间 单位秒(默认10s)
     */
    public static final int CONNECT_TIMEOUT = 10;

    /**
     * 写超时时间 单位秒(默认 0 , 不超时)
     */
    public static final int WRITE_TIMEOUT = 0;

    /**
     * 回复超时时间 单位秒(默认30s)
     */
    public static final int READ_TIMEOUT = 30;

    /**
     * 底层HTTP库所有的并发执行的请求数量
     */
    public static final int DISPATCHER_MAX_REQUESTS = 64;

    /**
     * 底层HTTP库对每个独立的Host进行并发请求的数量
     */
    public static final int DISPATCHER_MAX_REQUESTS_PER_HOST = 16;

    /**
     * 底层HTTP库中复用连接对象的最大空闲数量
     */
    public static final int CONNECTION_POOL_MAX_IDLE_COUNT = 32;

    /**
     * 底层HTTP库中复用连接对象的回收周期（单位分钟）
     */
    public static final int CONNECTION_POOL_MAX_IDLE_MINUTES = 5;

    /**
     * 自字符编码
     */
    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    /**
     * URL前缀
     */
    public static final String PREFIX = "https://co-sdk-dev.shimorelease.com";

    /**
     * content-type头
     */
    public static final String CONTENT_TYPE_HEADER = "Content-Type";

    public static final String SIGNATURE = "X-Shimo-Signature";
    public static final String TOKEN = "X-Shimo-Token";

    /**
     * 流
     */
    public static final String STREAM_MIME = "application/octet-stream";

    /**
     * json
     */
    public static final String JSON_MIME = "application/json";
}
