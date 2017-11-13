package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
    }

    public void NewAccount(View v){
        Intent intent = new Intent(getApplication(),newacount.class);
        startActivity(intent);
    }

    private void getData() {
        final ArrayList<User> UserList = new ArrayList<>();
        email = emailEdit.getText().toString();
        password = passwordEdit.getText().toString();

        //クエリを投げる
        Call<List<User>> call = ApiService.account("users.json?email="+email+"&password="+password);
        Log.d("AccountLogin","email："+email+"password："+password);
        try {
            call.enqueue(new Callback<List<User>>() {
                @Override                           //取得成功
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    Log.d("AccountLogin", "call onResponse");
                    UserList.addAll(response.body());
                    updateContainer(UserList);
                }
                @Override                           //取得失敗
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Log.d("Account", "call onFailure");
                    updateContainer(UserList);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateContainer(ArrayList<User> UserList) {

        int count=0;
        for (User user : UserList) {
            String account_name=(user.name);
            Log.d("AccountLogin", "アカウント名："+account_name);
            Intent intent=new Intent(getApplication(),MainActivity.class);
            startActivity(intent);

            count+=1;
        }
        if(count==0){
            emailEdit.getEditableText().clear();
            passwordEdit.getEditableText().clear();
        }
    }
}
