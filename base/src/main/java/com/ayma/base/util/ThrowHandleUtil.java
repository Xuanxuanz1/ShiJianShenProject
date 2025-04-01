package com.ayma.base.util;


import android.text.TextUtils;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.Response;

/**
 * @ 描述：常见错误统一处理
 * @ 作者：黄业良
 * @ 时间：2020/5/12 15:08
 * @ 包名：com.ayma.base.util
 * @ 类名：ThrowHandleUtil.java
 */
public class ThrowHandleUtil {

    public static Throwable generateThrowable(Throwable t) {
        if (t == null) {
            return generateThrowable("未知异常");
        }
        if (t instanceof SocketException) {
            return generateThrowable("网络连接异常，请检查网络连接");
        } else if (t instanceof JsonParseException || t instanceof JSONException || t instanceof ParseException) {
            return generateThrowable("数据解析异常");
        } else if (t instanceof UnknownHostException) {
            return generateThrowable("无法解析该域名或网络未连接/异常，请重试");
        } else if (t instanceof SocketTimeoutException) {
            return generateThrowable("响应超时，如当前网络信号不好，请稍后重试");
        } else if (t instanceof IllegalArgumentException) {
            return generateThrowable("参数异常");
        }
        if (TextUtils.isEmpty(t.getMessage())) {
            return generateThrowable("未知异常");
        } else if (t.getMessage().contains("timeout")) {
            return generateThrowable("请求超时");
        }

        return t;
    }

    public static Throwable generateThrowable(Response response) {
        if (response == null) {
            return generateThrowable("未知异常");
        }
        return generateThrowable(response.code() + ":" + response.message());
    }

    public static Throwable generateThrowable(String message) {
        if (message.contains("timeout")) {
            message = "请求超时";
        } else if (message.contains("null")) {
            message = "请求异常";
        }
        return new Throwable(message);
    }
}
