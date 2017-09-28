package com.example.a1521093.ap2_android;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class maker extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maker);

        TextView dai = (TextView)findViewById(R.id.kensakugamen);
        Intent intent = getIntent();
        String data = intent.getStringExtra("maker");
        dai.setText(data);

        LinearLayout layout = (LinearLayout) findViewById(R.id.loop);
        for(int i=0;i<8;i++) {
            View view = getLayoutInflater().inflate(R.layout.kategori_sub, null);
            layout.addView(view);
            TextView text = (TextView) view.findViewById(R.id.loop_name);
            final String meka =("商品"+(1+i));
            text.setText(meka);

            Button btn = (Button)findViewById(R.id.susumu);
            btn.setId(i);
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(),GPS.class);

                    intent.putExtra("syohin",meka);
                    startActivity(intent);
                }
            });
        }

        Button sendButton=(Button)findViewById(R.id.modoru);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),kategori.class);
                Intent kate_dai = getIntent();
                String kate = kate_dai.getStringExtra("kategori");
                intent.putExtra("kategori",kate);
                startActivity(intent);

            }
        });
        Button ken=(Button)findViewById(R.id.kensaku);
        ken.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                EditText SendValue = (EditText)findViewById(R.id.kensakutext);
                String syohin = SendValue.getText().toString();
                Intent intent=new Intent(getApplication(),GPS.class);
                intent.putExtra("syohin",syohin);
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