package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    EditText SendValue;
    Button SendEditTextValue;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SendEditTextValue = (Button)findViewById(R.id.kensaku);
        SendValue = (EditText)findViewById(R.id.edittext);

        Button kesyo=(Button)findViewById(R.id.kesyouhin);
        kesyo.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),kategori.class);
                startActivity(intent);

            }
        });

        Button sendButton=(Button)findViewById(R.id.kensaku);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),GPS.class);
                startActivity(intent);

            }
        });


        Button ken=(Button)findViewById(R.id.kaden);
        ken.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),kategori.class);
                startActivity(intent);

            }
        });
    }

   /* public void kensaku_onClick(View v) {
        intent = new Intent(getApplicationContext(), GPS.class);
        intent.putExtra("EdiTtEXTvALUE", SendValue.getText().toString());
        startActivity(intent);

    }
    
    public void keshouhin_onClick(View v) {
        Intent i = new Intent(this, kategori.class);
        startActivity(i);
    }

    public void kaden_onClick(View v) {
        Intent i = new Intent(this, kategori.class);
        startActivity(i);
    }
*/


}
