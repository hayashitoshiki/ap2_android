package com.example.a1521093.ap2_android;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.InputStream;
import java.net.URL;
import com.example.a1521093.ap2_android.kennsakukekaActivity;
//独自のListViewのレイアウト生成クラス

public class TopListView extends LinearLayout {
    private ImageView imageView;
    private TextView textViewShopName;

    public TopListView(Context context,int i) {
        this(context, null,i);
    }

    public TopListView(Context context, @Nullable AttributeSet attrs,int i) {
        this(context, attrs, 0, i);
    }
            //View生成
    public TopListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int i) {
        super(context, attrs, defStyleAttr);
        if(i==1){
            LayoutInflater.from(context).inflate(R.layout.list_top_adapter, this);
        }
        textViewShopName = (TextView) findViewById(R.id.shopNameText);
      //  imageView = (ImageView) findViewById(R.id.imageViewShop);
    }

            //Viewに値セット
    public void setProduct(final Product product) {
        textViewShopName.setText(product.getname());
        // textViewCompanyName.setText("aa");
         //textViewCategory.setText(product.getAddress());
        // textViewUpdate_at.setText(qiitaItem.getUpdated_at());
    }
}

