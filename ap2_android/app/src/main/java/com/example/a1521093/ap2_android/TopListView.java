package com.example.a1521093.ap2_android;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.InputStream;
import java.net.URL;

//独自のListViewのレイアウト生成クラス

public class TopListView extends LinearLayout {
    private ImageView imageView;
    private TextView textViewShopName;
    private TextView textViewCompanyName;
    private TextView textViewCategory;
    private TextView textViewUpdate_at;

    URL url;
    InputStream istream;

    public TopListView(Context context) {
        this(context, null);
    }

    public TopListView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
            //View生成
    public TopListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.list_top_adapter, this);
        textViewShopName = (TextView) findViewById(R.id.shopNameText);
        // textViewCompanyName = (TextView) findViewById(R.id.companyNameText);
        // textViewCategory = (TextView) findViewById(R.id.categoryText);
        // textViewUpdate_at = (TextView) findViewById(R.id.update_atText);
        //imageView = (ImageView) findViewById(R.id.imageViewShop);
    }

            //Viewに値セット
    public void setProduct(final Product product) {
        textViewShopName.setText(product.getname());
        // textViewCompanyName.setText("aa");
        // textViewCategory.setText(qiitaItem.getCategory());
        // textViewUpdate_at.setText(qiitaItem.getUpdated_at());



    }
}

