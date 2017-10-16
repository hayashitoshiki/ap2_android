package com.example.a1521093.ap2_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;

public class kategori extends  AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategori);

        TextView title = (TextView)findViewById(R.id.kensakugamen);
        Intent intent = getIntent();
        String kategori_daimei = intent.getStringExtra("kategori");
        title.setText(kategori_daimei+"のカテゴリ");

        LinearLayout layout = (LinearLayout) findViewById(R.id.loop);
        for(int i=0;i<8;i++) {
            View view = getLayoutInflater().inflate(R.layout.kategori_sub, null);
            layout.addView(view);
            TextView text = (TextView) view.findViewById(R.id.loop_name);
            final String kategori_name =("カテゴリ"+(i+1));
            text.setText(kategori_name);

            Button btn = (Button)findViewById(R.id.susumu);
            btn.setId(i);

            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(),maker.class);
                    intent.putExtra("kategori2",kategori_name);
                    Intent kate_dai = getIntent();
                    String kategori = kate_dai.getStringExtra("kategori");
                    intent.putExtra("kategori",kategori);
                    startActivity(intent);
                }
            });
        }

        Button sendButton=(Button)findViewById(R.id.modoru);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),MainActivity.class);
                startActivity(intent);

            }
        });
        Button ken=(Button)findViewById(R.id.kensaku);
        ken.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                EditText SendValue = (EditText)findViewById(R.id.kensakutext);
                String syohin_name = SendValue.getText().toString();
                Intent intent=new Intent(getApplication(),GPS.class);
                intent.putExtra("syohin",syohin_name);
                startActivity(intent);

            }
        });
    }
    public void home_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void kensaku_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
