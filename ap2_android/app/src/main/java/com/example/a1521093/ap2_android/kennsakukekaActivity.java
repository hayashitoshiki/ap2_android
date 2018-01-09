package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.a1521093.ap2_android.R.id.listView;

public class kennsakukekaActivity extends AppCompatActivity {
    private  static int count;
    private int bunki;
    ArrayAdapter<Product> adapter;
    ListView mListView;
    ApiService ApiService;
    TopListAdapter topListAdapter;

    public static double[] store_lati = new double[100];
    public static double[] store_lon = new double[100];
    public static String[] store_name=new String[100];
    public static int stock[] = new int[100];
    public static String[] store_address = new String[100];
    public static String[] store_image = new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kensakukeka);

        Intent intent = getIntent();
        bunki = intent.getIntExtra("switch",0);
        Log.d("分岐確認","スイッチ："+bunki);

        TextView title = (TextView)findViewById(R.id.procutd_name);
        title.setText(Product.product_name);

        Log.d("kennsakukekaActivity", "メーカーID："+Product.product_id);
        //ArrayAdapterオブジェクト生成
        adapter=new ArrayAdapter<Product>(kennsakukekaActivity.this, android.R.layout.simple_list_item_1);
        topListAdapter = new TopListAdapter(getApplicationContext(), R.layout.kekka_sub);
        mListView = (ListView) findViewById(listView);
        ApiService = Provider.provideApiService();
        getData();
        //サンプルのListViewに独自で造ったListViewの適用
        mListView.setAdapter(topListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (view.getId()) {
                    case R.id.tizu:
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);

                        intent.setData(Uri.parse("http://maps.google.com/maps?saddr="+User.user_latitude+","+User.user_longitude+"&daddr="+store_lati[position]+","+store_lon[position]));
                        startActivity(intent);
                        break;
                    case R.id.susumu:
                        Intent in = new Intent(getApplication(), Product_Barcode.class);
                        in.putExtra("switch",bunki);
                        startActivity(in);

                        break;
                }
            }
        });
  }

    public void modoru_onClick(View v) {
        Intent intent;
        Intent get = getIntent();
       if(bunki==1) {
           intent = new Intent(this, SyohinItiran.class);
       }else{
           intent = new Intent(this, KensakuRoot.class);
           String kensaku = get.getStringExtra("kensaku");
           intent.putExtra("kensaku",kensaku);
        }
        startActivity(intent);
    }

    public void homeButton(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void accountButton(View v){
        Intent i = new Intent(this, Account.class);
        startActivity(i);
    }

    public void kensaku_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void getData() {
        final ArrayList<Product> aProductList = new ArrayList<>();

        //クエリを投げる
        Call<List<Product>> call = ApiService.items("prices.json?product_id="+Product.product_id);
        try {
            call.enqueue(new Callback<List<Product>>() {
                @Override                             //取得成功
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    Log.d("MainActivity", "call onResponse");
                    aProductList.addAll(response.body());
                    updateContainer(aProductList);
                }
                @Override                           //取得失敗
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Log.d("MainActivity", "call onFailure");
                    updateContainer(aProductList);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ListViewに値入れるクラス
    private void updateContainer(ArrayList<Product> aProductList) {
        count=0;
        int i=0;
        for (Product product : aProductList) {
            Log.d("サブカテゴリ", product.shop_id+"カウント="+ i);
             stock[i] = product.count;
            i++;
            //遷移時に投げる用のテキスト取得と格納
            final ArrayList<Product> aList = new ArrayList<>();
            //クエリを投げる
            Call<List<Product>> call = ApiService.items("shops.json?id="+product.shop_id);
            Log.d("kennsakukekaActivity", "店舗ID：" + product.shop_id);
            try {
                call.enqueue(new Callback<List<Product>>() {
                    @Override
                    //取得成功
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        aList.addAll(response.body());
                       for (Product product : aList) {
                           store_name[count] = (product.name);
                           store_lati[count] = (product.latitude);
                           store_lon[count] = (product.longitude);
                           store_address[count]=(product.address);
                           store_image[count] = (product.image);
                            //指定のListViewに格納
                           topListAdapter.setDatas(aList,3);
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

