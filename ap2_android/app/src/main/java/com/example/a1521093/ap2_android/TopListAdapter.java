package com.example.a1521093.ap2_android;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


//独自のListViewレイアウト生成クラス

public class TopListAdapter extends BaseAdapter {

    private ArrayList<TopListView> aProductList;
    private Context context;
    private LayoutInflater inflater;
    private int resourcedId;

    private static class ViewHolder {
        public Button editButton;
        public Button susumuButton;

        public TextView textViewstock;
        public TextView textViewname;
        public TextView textViewaddress;
        public TextView textViewdistance;
    }
    public TopListAdapter(Context context, int resourcedId) {
        this.context = context;
        this.aProductList = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourcedId = resourcedId;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;

        Log.d("通るか調査", "店舗ID：" + position+"レイアウトID"+resourcedId+"グループ"+parent);

        if (convertView == null) {
            Log.d("通るか調査1.5", "店舗ID：" + position);

            if(resourcedId==2130968621) {
                Log.d("通るか調査1.5.5", "店舗ID：" + position);

                convertView = inflater.inflate(resourcedId, null);
                holder = new ViewHolder();
                holder.editButton = (Button) convertView.findViewById(R.id.tizu);
                holder.susumuButton = (Button) convertView.findViewById(R.id.susumu);

                holder.textViewname = (TextView)convertView.findViewById(R.id.syouhinmei);
                holder.textViewstock = (TextView)convertView. findViewById(R.id.kosuu);
                holder.textViewaddress = (TextView)convertView.findViewById(R.id.zyusyo);
                holder.textViewdistance = (TextView)convertView.findViewById(R.id.kyori);
                convertView.setTag(holder);
            }else{
                  return aProductList.get(position);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(resourcedId==2130968621) {
            Log.d("通るか調査２", "店舗ID：" + position);
            kennsakukekaActivity keka= new kennsakukekaActivity();
           int stock = keka.getstock(position);
            String store_name = keka.getstore_name(position);
            String store_address = keka.getaddress(position);

            double user_lon  = keka.getuser_lon();
            double user_lati = keka.getuser_lati();
            double store_lati = keka.getstore_lati(position);
            double store_lon = keka.getstore_lon(position);
            double distance = getDistance(user_lati, user_lon, store_lati, store_lon);
            int kyori_A = (int)distance;
            if(kyori_A>10000) {
                kyori_A = kyori_A / 1000;
                holder.textViewdistance.setText(kyori_A + "km");
            }else {
                holder.textViewdistance.setText(kyori_A + "m");
            }
             holder.textViewname.setText(store_name);
            holder.textViewaddress.setText(store_address);
            holder.textViewstock.setText(stock+"個");
            holder.editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ListView) parent).performItemClick(view, position,R.id.tizu);
                }
            });

            holder.susumuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ListView) parent).performItemClick(view, position,R.id.tizu);
                }
            });
        }
        return convertView;
    }


    private double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) +  Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        double miles = dist * 60 * 1.1515;
        return (miles * 1.609344*1000);
    }

    private double rad2deg(double radian) {
        return radian * (180f / Math.PI);
    }

    public double deg2rad(double degrees) {
        return degrees * (Math.PI / 180f);
    }

    //データ取得
    public void setDatas(ArrayList<Product> aProductList,int i) {
        for (Product product : aProductList) {
            TopListView view = new TopListView(context,i);
            if(i==1){
            //TopListViewに値を渡してレイアウトセット
                view.setProduct(product);
            }
            this.aProductList.add(view);
        }
    }
}
