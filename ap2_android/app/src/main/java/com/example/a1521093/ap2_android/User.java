package com.example.a1521093.ap2_android;

//ユーザJSON用クラス

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    //  JSONとの対応
    @Expose
    @SerializedName("email")
    public  String email;
    @Expose
    @SerializedName("name")
    public String name;
    @Expose
    @SerializedName("password")
    public String password;
    @Expose
    @SerializedName("point")
    public int point;



}
