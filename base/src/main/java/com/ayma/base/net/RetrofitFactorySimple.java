package com.ayma.base.net;


import com.ayma.base.BuildConfig;
import com.ayma.base.util.CommonUtil;
import com.ayma.base.util.Logger;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ 描述：retrofit工厂类，此类作为展示如何使用，具体使用时建议根据实际业务需求创建RetrofitFactory
 * @ 作者：黄业良
 * @ 包名：com.ayma.base.net
 * @ 类名：RetrofitFactorySimple.java
 */
public class RetrofitFactorySimple {
    private String TAG = "RetrofitFactorySimple";
    public static final String KEY_TOKEN = "accessToken";
    public static final String DEFAULT_TOKEN = "Bearer ";
    private static final RetrofitFactorySimple ourInstance = new RetrofitFactorySimple();
    private static final int RETRY_TIMES_MAX = 5;
    private Retrofit retrofit;      //普通接口
    private Gson gson;

    public static RetrofitFactorySimple getInstance() {
        return ourInstance;
    }

    private RetrofitFactorySimple() {
        initRetrofit();
    }

    private void initRetrofit() {
        gson = new Gson();
        retrofit = new Retrofit.Builder()
                .baseUrl("www.xxxx")//接口地址
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(initClient())
                .build();
    }


    private OkHttpClient initClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Interceptor myInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
//                //header添加token信息
                //TODO 这里展示如何添加统一请求头
                Request request = chain.request().newBuilder()
                        .addHeader(KEY_TOKEN, DEFAULT_TOKEN).build();
                Logger.i(TAG, request.toString());
                //在这里获取到request后就可以做任何事情了
                String paramsStr = "";
                Headers headers = request.headers();
                if (BuildConfig.DEBUG) {
                    Set<String> names = headers.names();
                    StringBuilder builder1 = new StringBuilder();
                    for (String s : names) {
                        builder1.append(s).append(":").append(headers.get(s));
                    }
                    //输出请求头信息
                    Logger.i(TAG, "\nheaders:\n" + builder1.toString() + "\n");
                }
                RequestBody requestBody = request.body();
                if (requestBody != null && BuildConfig.DEBUG) {
                    Buffer buffer = new Buffer();
                    requestBody.writeTo(buffer);
                    Charset charset = StandardCharsets.UTF_8;
                    MediaType contentType = requestBody.contentType();
                    if (contentType != null) {
                        charset = contentType.charset(StandardCharsets.UTF_8);
                    }
                    paramsStr = buffer.readString(charset);
                    //输出请求地址以及参数
                    Logger.i(TAG, "\n" + request.url() + "\n" + paramsStr);
                }
                return chain.proceed(request);
            }
        };
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    //打印retrofit日志
                    Logger.i(TAG, "retrofitBack = " + message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        Interceptor responseDecodeInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response originalResponse = chain.proceed(chain.request());
                ResponseBody body = originalResponse.body();
                //重新构建Response
                okhttp3.Response.Builder builder1 = new okhttp3.Response.Builder()
                        .code(originalResponse.code())
                        .headers(originalResponse.headers())
                        .message(originalResponse.message())
                        .handshake(originalResponse.handshake())
                        .cacheResponse(originalResponse.cacheResponse())
                        .networkResponse(originalResponse.networkResponse())
                        .priorResponse(originalResponse.priorResponse())
                        .protocol(originalResponse.protocol())
                        .request(originalResponse.request())
                        .receivedResponseAtMillis(originalResponse.receivedResponseAtMillis())
                        .sentRequestAtMillis(originalResponse.sentRequestAtMillis());
                String content = CommonUtil.decodeUnicode(body != null ? body.string() : null);
                //TODO 如需要可在此处对返回结果进行统一解密操作
//                try {
//                    content = DesHelper.DecryptDoNet(content);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    content = "";
//                }
//                Logger.i(TAG, "retrofitBack Decrypt = " + content);
                ResponseBody responseBody = ResponseBody.create(body != null ? body.contentType() : null, content.replace("\n", "").replace("\r", ""));
                builder1.body(responseBody);
                return builder1.build();
            }
        };

//        RetryInterceptor retryInterceptor = new RetryInterceptor();
        return builder
                .addInterceptor(responseDecodeInterceptor)
                .addInterceptor(myInterceptor)
//                .addInterceptor(retryInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)//设置超时时间为30s
                .build();
    }

    public void resetRetrofit() {
        initRetrofit();
    }


    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

    public ApiSimple create() {
        return retrofit.create(ApiSimple.class);
    }


    public Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }


    public class RetryInterceptor implements Interceptor {
        int retryTimes = 0;

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            while (!response.isSuccessful() && retryTimes < RETRY_TIMES_MAX) {
                retryTimes++;
                Logger.i("RetryInterceptor", "连接失败，第" + retryTimes + "次尝试重连");
                response = chain.proceed(request);
            }
            return response;
        }
    }
}
