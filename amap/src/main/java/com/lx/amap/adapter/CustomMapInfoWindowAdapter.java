package com.lx.amap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.bumptech.glide.Glide;
import com.lx.amap.R;

/**
 * Created by LucianBen on 2019/6/5
 * Describe:
 */
public class CustomMapInfoWindowAdapter implements AMap.InfoWindowAdapter {
    private Context context;

    public CustomMapInfoWindowAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View view = LayoutInflater.from(context).inflate(R.layout.map_info_window, null);
        setViewContent(marker, view);
        return view;
    }

    //这个方法根据自己的实体信息来进行相应控件的赋值
    private void setViewContent(Marker marker, View view) {
        //实例：
//        HomeStore.DataEntity storeInfo = (HomeStore.DataEntity) marker.getObject();
        ImageView ivPic = (ImageView) view.findViewById(R.id.mapInfoWindowImage);
        Glide.with(context).load("").centerCrop().into(ivPic);
        TextView mapInfoWindowText = (TextView) view.findViewById(R.id.mapInfoWindowText);
        mapInfoWindowText.setText("我的名字");

    }

    //提供了一个给默认信息窗口定制内容的方法。如果用自定义的布局，不用管这个方法。
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

}
