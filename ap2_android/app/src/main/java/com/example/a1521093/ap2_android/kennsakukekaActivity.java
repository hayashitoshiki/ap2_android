package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kennsakukekaActivity extends AppCompatActivity {
    public static double user_lat;
    public static double user_lon;

    private ApiService ApiService;
    private TopListAdapter topListAdapter;
    ArrayAdapter<Product> adapter;
    ListView mListView;
    int sub_category_id;
    int product_id;
    public static int count;
    int bunki;

    protected double[] store_lati = new double[100];
    protected double[] store_lon = new double[100];
    protected String[] store_name=new String[100];
    public static int stock[] = new int[100];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kensakukeka);

        Intent intent = getIntent();
        bunki = intent.getIntExtra("switch",0);
        TextView title = (TextView)findViewById(R.id.procutd_name);
        String  product_name = intent.getStringExtra("product_name");
        Log.d("kennsakukekaActivity", "商品名："+product_name);
        title.setText(product_name);

        user_lon=intent.getDoubleExtra("user_lon",0);
        user_lat = intent.getDoubleExtra("user_lat",0);

        sub_category_id = intent.getIntExtra("sub_category_id",0);
        product_id = intent.getIntExtra("product_id",0);
        Log.d("kennsakukekaActivity", "メーカーID："+product_id);
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

    public void modoru_onClick(View v) {
        Intent intent;
        Intent get = getIntent();
       if(bunki==1) {
           intent = new Intent(this, SyohinItiran.class);

           String main_category_name = get.getStringExtra("main_category_name");
           int main_category_id = get.getIntExtra("main_category_id",0);
           String maker_name = get.getStringExtra("maker_name");
           int maker_id = get.getIntExtra("maker_id",0);
           String sub_category_name = get.getStringExtra("sub_category_name");
           int sub_category_id = get.getIntExtra("sub_category_id",0);
           String product_name = get.getStringExtra("product_name");
           int product_id = get.getIntExtra("product_id",0);
           Log.d("店舗", "main"+main_category_name+":"+main_category_id+"：：sub"+sub_category_name+":"+sub_category_id+"：：maker"+maker_name+":"+maker_id);

           intent.putExtra("main_category_name",main_category_name);
           intent.putExtra("main_category_id",main_category_id );
           intent.putExtra("maker_name",maker_name);
           intent.putExtra("maker_id",maker_id );
           intent.putExtra("sub_category_name",sub_category_name);
           intent.putExtra("sub_category_id",sub_category_id );
           intent.putExtra("product_name",product_name);
           intent.putExtra("product_id",product_id);
       }else{
           intent = new Intent(this, KensakuRoot.class);
           String kensaku = get.getStringExtra("kensaku");
           intent.putExtra("kensaku",kensaku);
        }
        startActivity(intent);
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
            Log.d("kennsakukekaActivity", "店舗ID：" + product.getShop_id());
            try {
                call.enqueue(new Callback<List<Product>>() {
                    @Override
                    //取得成功
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        aList.addAll(response.body());
                       for (Product product : aList) {
                            store_name[count] = (product.getname());
                            store_lati[count] = (product.getlatitude());
                            store_lon[count] = (product.getlongitude());
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

