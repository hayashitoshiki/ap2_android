package com.example.a1521093.ap2_android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

//独自のListViewレイアウト生成クラス

public class TopListAdapter extends BaseAdapter {

    private ArrayList<TopListView> aProductList;
    private Context context;

    public TopListAdapter(Context context) {
        this.context = context;
        this.aProductList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return aProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return aProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            return aProductList.get(position);
        } else {
            return convertView;
        }
    }
                    //データ取得
    public void setDatas(ArrayList<Product> aProductList,int i) {
        for (Product product : aProductList) {
            TopListView view = new TopListView(context,i);
            if(i==1){
            //TopListViewに値を渡してレイアウトセット
                view.setProduct(product);
            }else{
                view.setProduct2(product);
            }
            this.aProductList.add(view);
        }
    }
}
