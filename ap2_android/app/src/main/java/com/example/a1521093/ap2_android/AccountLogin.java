package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountLogin extends AppCompatActivity {
    private ApiService ApiService;
    private EditText emailEdit;
    private EditText passwordEdit;
    public static String email;
    public static String password;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        emailEdit = (EditText)findViewById(R.id.email);
        passwordEdit = (EditText)findViewById(R.id.password);
        ApiService = Provider.provideApiService();

    }
    public void Login(View v){
        getData();
        Intent intent=new Intent(getApplication(),AccountLogin.class);
        startActivity(intent);
    }

    public void NewAccount(View v){
        Intent intent = new Intent(getApplication(),newacount.class);
        startActivity(intent);
    }

    public static  String getemail(){return email;}

    public static String getpassword(){return password;}

    private void getData() {
        final ArrayList<Product> aProductList = new ArrayList<>();
        email = emailEdit.getText().toString();
        password = passwordEdit.getText().toString();

        //クエリを投げる
        Call<List<Product>> call = ApiService.items("users.json?email="+email+"&password="+password);
        Log.d("AccountLogin","email："+email+"password："+password);
        try {
            call.enqueue(new Callback<List<Product>>() {
                @Override                           //取得成功
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    Log.d("AccountLogin", "call onResponse");
                    aProductList.addAll(response.body());
                    Log.d("AccountLogin", aProductList.toString());

                    updateContainer(aProductList);
                }
                @Override                           //取得失敗
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Log.d("Account", "call onFailure");
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

    private void updateContainer(ArrayList<Product> aProductList) {

        for (Product product : aProductList) {
            String account_name=(product.getname());
            Log.d("AccountLogin", "アカウント名："+account_name);
            if(account_name!=null){
                Intent intent=new Intent(getApplication(),MainActivity.class);
                startActivity(intent);
            }

        }

    }
}
