package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

public class newacount extends AppCompatActivity {
    private AccountLogin accountlogin;
    private ApiService ApiService;
    private Button button2 =null;
    private EditText name = null;
    private EditText email = null;
    private EditText password1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newacount);

        //戻るボタン
        Button modorou=(Button)findViewById(R.id.modoru);
        modorou.setOnClickListener(new View.OnClickListener(){
            @Override

            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),AccountLogin.class);
                startActivity(intent);
            }
        });

        Button sousin=(Button)findViewById(R.id.button2);
        sousin.setOnClickListener(new View.OnClickListener(){
            @Override

            //パスワード比較
            public  void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.name);
                EditText email = (EditText) findViewById(R.id.email);
                EditText password1 = (EditText) findViewById(R.id.password1);
                EditText password2 = (EditText) findViewById(R.id.password2);

                String user_name = name.getText().toString();
                final String user_email = email.getText().toString();
                final String user_password1 = password1.getText().toString();
                String user_password2 = password2.getText().toString();

                if ((user_name.length() != 0) && (user_email.length() != 0) && (user_password1. length() != 0) && (user_password2.length() != 0)) {
                    if (user_password1.equals(user_password2) == false) {
                        //不一致の場合の処理
                        Intent intent = new Intent(getApplication(), newacount.class);
                        startActivity(intent);
                    } else {
                        final ArrayList<User> User = new ArrayList<>();
                        ApiService = Provider.provideApiService();

                        final User user = new User();
                        user.name = user_name;
                        user.email = user_email;
                        user.password = user_password1;
                        user.point = 0;
                        Log.d("NewAccount", "ユーザー名："+user.name+"、メール："+user.email+"、パスワード："+user.password+"、ポイント："+user.point);

                        Call<List<User>> call = ApiService.user(user);
                        try {
                        call.enqueue(new Callback<List<User>>() {
                            @Override
                            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                                Log.d("NewAccount", "call onResponse");
                                User.addAll(response.body());
                            }

                            @Override
                            public void onFailure(Call<List<User>> call, Throwable t) {
                                Log.d("NewAccount", "call onFailure");
                            }
                        });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        accountlogin.setemail(user.email);
                        accountlogin.setpassword(user.password);
                        Log.d("NewAccount", "ユーザー名："+user.name+"、メール："+user.email+"、パスワード："+user.password+"、ポイント："+user.point);

                        Intent intent = new Intent(getApplication(), MainActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Intent intent = new Intent(getApplication(), newacount.class);
                    startActivity(intent);
                }
            }
        });
    }
}


