package com.example.a1521093.ap2_android;

//mono-chika(json)取得時の格納クラス

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @Expose
    @SerializedName("id")
    public int id;

    @Expose
    @SerializedName("shop_id")
    public int shop_id;

    @Expose
    @SerializedName("address")
    public String address;

    @Expose
    @SerializedName("name")
    public String name;

    @Expose
    @SerializedName("longitude")
    public double longitude;

    @Expose
    @SerializedName("latitude")
   public double latitude;

    @Expose
    @SerializedName("count")
    public int count;

    @Expose
    @SerializedName("image")
    public String image;

    public static int maker_id;
    public static String maker_name;
    public static int  main_category_id;
    public static String main_category_name;
    public static int sub_category_id;
    public static String sub_category_name;
    public static int product_id;
    public static String product_name;
    public static String product_image;

    @Override
    public String toString() {
        return name ;
    }

}
