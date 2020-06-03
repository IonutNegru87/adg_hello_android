package com.inegru.siit.myapplication.week8;

import com.inegru.siit.myapplication.BuildConfig;
import com.inegru.siit.myapplication.week8.interceptors.QueryParamInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

final class NetworkHelper {

    private static Retrofit retrofit;
    /**
     * By removing the leading /, the URL then becomes relative and will combine with the path segments which are part of the base URL.
     * Otherwise the base URL will be stripped and the result will be without "/3/movie/"
     */
    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static final String API_KEY = "0b808e56d790e7192b1fd1c794964caa";
    private static final String DEFAULT_LANGUAGE = "en-US";

    private NetworkHelper() {
        // Hide
    }

    static Retrofit getRetrofit() {
        if (retrofit == null) {
            // Add any custom configuration to the http cient used by Retrofit
            OkHttpClient.Builder httpClient = getClient();
            // Add the api key as query for all requests
            httpClient.addInterceptor(new QueryParamInterceptor("api_key", API_KEY));
            // Add language as query for all requests
            httpClient.addInterceptor(new QueryParamInterceptor("language", DEFAULT_LANGUAGE));

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    // Add the converter from Json to POJO
                    .addConverterFactory(GsonConverterFactory.create())
                    // Bind the http client with the custom config to retrofit instance
                    .client(httpClient.build())
                    // Build the Retrofit instance
                    .build();
        }

        return retrofit;
    }

    private static OkHttpClient.Builder getClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(logging);
        }
        return client;
    }
}
