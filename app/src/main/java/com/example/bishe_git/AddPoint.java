package com.example.bishe_git;


import android.os.Bundle;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class AddPoint {
    //点的集合
    public static List<OverlayOptions> options = new ArrayList<>();

    //往地图上添加一个覆盖点
    public  void addPoint(double weidu, double jingdu, int id) {
        Bundle mBundle = new Bundle();
        mBundle.putInt("id", id);
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.dingwei1);
        LatLng point = new LatLng(weidu, jingdu);
        OverlayOptions option = new MarkerOptions()
                .position(point)//option的坐标
                .icon(bitmap) //图标
                .draggable(false)//是否可以拖动
//                .flat(false)//设置平贴地图，在地图中双指下拉查看效果
                .alpha(0.9f)//不透明度
                .animateType(MarkerOptions.MarkerAnimateType.grow) //设置动画  有MarkerOptions.MarkerAnimateType.drop掉下来 MarkerOptions.MarkerAnimateType.grow生长MarkerOptions.MarkerAnimateType.jump   跳跃
                .perspective(true)//是否开启近大远小效果
                .extraInfo(mBundle);//marker携带的信息
        options.add(option);
    }

    //往地图上添加一个覆盖点
    public static OverlayOptions addLatLng(LatLng latLng, int id) {
        Bundle mBundle = new Bundle();
        mBundle.putInt("id", id);
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.dingwei1);
        OverlayOptions option = new MarkerOptions()
                .position(latLng)//option的坐标
                .icon(bitmap) //图标
                .draggable(false)//是否可以拖动
//                .flat(false)//设置平贴地图，在地图中双指下拉查看效果
                .alpha(0.9f)//不透明度
                .animateType(MarkerOptions.MarkerAnimateType.jump) //设置动画  有MarkerOptions.MarkerAnimateType.drop掉下来 MarkerOptions.MarkerAnimateType.grow生长MarkerOptions.MarkerAnimateType.jump   跳跃
                .perspective(true)//是否开启近大远小效果
                .extraInfo(mBundle);//marker携带的信息
        return option;
    }
}
