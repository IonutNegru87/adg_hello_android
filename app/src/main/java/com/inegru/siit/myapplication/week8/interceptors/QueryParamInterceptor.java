package com.inegru.siit.myapplication.week8.interceptors;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class QueryParamInterceptor implements Interceptor {

    private final String name;
    private final String value;

    public QueryParamInterceptor(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request original = chain.request();

        HttpUrl.Builder urlBuilder = original.url().newBuilder();
        urlBuilder.addQueryParameter(name, value);

        Request request = original.newBuilder()
                .url(urlBuilder.build())
                .build();

        return chain.proceed(request);
    }
}
