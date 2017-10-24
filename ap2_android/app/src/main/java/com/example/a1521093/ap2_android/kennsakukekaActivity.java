package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kennsakukekaActivity extends AppCompatActivity {
    //データベースlati
   // double store_lat=(35.605802);
    //データベースlong
   // double store_lon=(139.735325);
    public static double user_lat;
    public static double user_lon;
    public static int stock[] = new int[100];


    private ApiService ApiService;
    private TopListAdapter topListAdapter;
    ArrayAdapter<Product> adapter;
    private Product product;
    ListView mListView;
    String sub_category_name;
    int sub_category_id;
    int product_id;
    String main_category_name;
    public static int count;

    protected double[] store_lati = new double[100];
    protected double[] store_lon = new double[100];
    protected String[] scenes=new String[100];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kensakukeka);

        Intent intent = getIntent();
        TextView title = (TextView)findViewById(R.id.procutd_name);
       String  product_name = intent.getStringExtra("product_name");
        Log.d("MainActivity", "商品名==="+product_name);
        title.setText(product_name);


        user_lon=intent.getDoubleExtra("user_lon",0);
         user_lat = intent.getDoubleExtra("user_lat",0);
        double distance = getDistance(user_lat, user_lon, store_lati[0], store_lon[0] );
        int kyori_A = (int)distance;
        String kyori_text =(""+kyori_A);
        final String data = intent.getStringExtra("syohin");


        sub_category_id = intent.getIntExtra("Sub_category_id",0);
        product_id = intent.getIntExtra("product_id",0);
        Log.d("MainActivity", "メーカーID=aaaaa"+product_id);
        //ArrayAdapterオブジェクト生成
        adapter=new ArrayAdapter<Product>(kennsakukekaActivity.this, android.R.layout.simple_list_item_1);
        topListAdapter = new TopListAdapter(getApplicationContext());
        mListView = (ListView) findViewById(R.id.listView);
        ApiService = Provider.provideApiService();
        getData();
        //サンプルのListViewに独自で造ったListViewの適用
        mListView.setAdapter(topListAdapter);
  }
    public double getuser_lati(){
        return user_lat;
    }
    public double getuser_lon(){
        return user_lon;
    }
    public int getstock(){return stock[count];}
    private double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) +  Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        double miles = dist * 60 * 1.1515;
        return (miles * 1.609344*1000);
    }

    private double rad2deg(double radian) {
        return radian * (180f / Math.PI);
    }

    public double deg2rad(double degrees) {
        return degrees * (Math.PI / 180f);
    }

    public void modoru_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

    public void home_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void kensaku_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void getData() {
        final ArrayList<Product> aProductList = new ArrayList<>();

        //クエリを投げる
        Call<List<Product>> call = ApiService.items("prices.json?product_id="+product_id);
        try {

            call.enqueue(new Callback<List<Product>>() {
                @Override
                //取得成功
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    Log.d("MainActivity", "call onResponse");
                    aProductList.addAll(response.body());
                    Log.d("MainActivity", aProductList.toString());
                    updateContainer(aProductList);
                }
                @Override                           //取得失敗
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Log.d("MainActivity", "call onFailure");
                    Log.d("MainActivity", t.getMessage());
                    Log.d("MainActivity", aProductList.toString());
                    updateContainer(aProductList);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            updateContainer(aProductList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ListViewに値入れるクラス
    private void updateContainer(ArrayList<Product> aProductList) {
        count=0;
        int i=0;
        for (Product product : aProductList) {
            Log.d("サブカテゴリ", product.getShop_id()+"カウント="+ i);
             stock[i] = product.getcount();
            i++;
            //遷移時に投げる用のテキスト取得と格納
            final ArrayList<Product> aList = new ArrayList<>();
            //クエリを投げる
            Call<List<Product>> call = ApiService.items("shops.json?id="+product.getShop_id());
            Log.d("kennsakukekaActivity", "aaa" + product.getShop_id());
            try {
                call.enqueue(new Callback<List<Product>>() {
                    @Override
                    //取得成功
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        aList.addAll(response.body());
                        Log.d("kennsakukekaActivity", "メーカーID="+ product_id+"商品ID="+product_id);
                        for (Product product : aList) {
                            Log.d("kennsakukekaActivity", "aaa" + product.getname()+":"+user_lon);
                            scenes[count]=(product.getname());
                            store_lati[count]=(product.getlatitude());
                            store_lon[count]=(product.getlongitude());
                            //指定のListViewに格納
                            topListAdapter.setDatas(aList,2);
                            topListAdapter.notifyDataSetChanged();
                            count++;
                        }
                    }
                    @Override                           //取得失敗
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

