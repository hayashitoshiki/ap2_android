package com.example.a1521093.ap2_android;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Url;

//クエリ取得、生成クラス
public interface ApiService {
    @GET
    //GET単品のため
    Call<List<Product>> items(@Url String url);

    @GET
    Call<List<User>> account(@Url String url);

    @POST("users.json")
    Call<List<User>> user( @Body User user);
}
