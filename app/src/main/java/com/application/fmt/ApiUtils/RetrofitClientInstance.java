package com.application.fmt.ApiUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    public static final String BASE_URL = "http://18.191.197.193/users/";


    static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cache(null)
            .build();

    public static Retrofit getRetrofitInstance(String baseUrl) {

        retrofit = new retrofit2.Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit;
    }
}