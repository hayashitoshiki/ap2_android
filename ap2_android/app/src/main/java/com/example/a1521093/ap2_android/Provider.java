package com.example.a1521093.ap2_android;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
public class Provider {
    private static ApiService ApiService = null;
    public static ApiService provideApiService() {
        if (ApiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://54.238.225.122/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            ApiService = retrofit.create(ApiService.class);
        }
        return ApiService;
    }
}
