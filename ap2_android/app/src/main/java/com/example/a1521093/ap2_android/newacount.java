package com.example.a1521093.ap2_android;
/**
 * Created by 1521077 on 2017/11/04.
 */
import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class newacount extends AppCompatActivity {

    private ApiService ApiService;
    private Button button2 =null;
    private EditText editText7 = null;
    private EditText editText8 = null;
    private EditText editText9 = null;

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
            public  void onClick(View v){
                EditText  a1 = (EditText)findViewById(R.id.editText9);
                EditText  a2 = (EditText)findViewById(R.id.editText10);
                String b1 = a1.getText().toString();
                String b2 = a2.getText().toString();

                if(b1.equals(b2) == false){
                    //不一致の場合の処理
                    Intent intent=new Intent(getApplication(),newacount.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getApplication(),MainActivity.class);
                    startActivity(intent);
                }
            }


        });
    }
}


