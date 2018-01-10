package com.example.a1521093.ap2_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                switch (view.getId()) {
                    case R.id.tizu:
                        AlertDialog.Builder alertDlg = new AlertDialog.Builder(kennsakukekaActivity.this);
                        alertDlg.setTitle("”この商品”をお気に入り登録しますか？");
                        alertDlg.setMessage("”この店舗”でのこの商品がお気に入りに登録されます");
                        alertDlg.setPositiveButton("はい", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("http://maps.google.com/maps?saddr="+User.user_latitude+","+User.user_longitude+"&daddr="+store_lati[position]+","+store_lon[position]));
                                startActivity(intent);
                            }
                        });
                        alertDlg.setNegativeButton("いいえ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent();
                                        intent.setAction(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse("http://maps.google.com/maps?saddr="+User.user_latitude+","+User.user_longitude+"&daddr="+store_lati[position]+","+store_lon[position]));
                                        startActivity(intent);
                                    }
                                });
                        alertDlg.create().show();
                }
            }
        });
  }


    public void Home_Button(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void Favorite_Button(View v){
        Intent i = new Intent(this, Favorite.class);
        startActivity(i);
    }

    public void Account_Button(View v){
        Intent i = new Intent(this, Account.class);
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

