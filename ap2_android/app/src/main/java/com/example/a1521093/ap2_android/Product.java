package com.example.a1521093.ap2_android;

//mono-chika(json)取得時のデータ格納クラス

public class Product {
    private int id;
    private int maker_id;
    private int  main_category_id;
    private int sub_category_id;
    private int shop_id;
    private String shop_name;
    private String shop_image;
    private String address;
    private String name;
    private double longitude;
    private double latitude;

    public Product(int id, int maker_id, int main_category_id, int sub_category_id, int shop_id,
                   String shop_name, String shop_image, String address
                     ,String name, double longitude, double latitude) {

        this.id = id;
        this.maker_id = maker_id;
        this.main_category_id = main_category_id;
        this.sub_category_id = sub_category_id;
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.shop_image = shop_image;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
    }

    public int getid(){return id;}

    public int getmaker_id(){return maker_id;}

    public int getmain_category_id(){return main_category_id;}

    public int getsub_category_id(){return sub_category_id;}

    public int getShop_id() {
        return shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getname() {
        return name;
    }

    public String getShop_image() {
        return shop_image;
    }

    public String getAddress() {
        return address;
    }

    public double getlongitude(){return longitude;}

    public double latitude(){return latitude;}


    @Override
    public String toString() {
        return name ;
    }

}
