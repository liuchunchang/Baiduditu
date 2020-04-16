package com.example.bishe_git;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_jingdian extends Fragment {
    private MyListener myListener;

    //定义回调接口
    public interface MyListener {
        public void sendValue_jingdian(String value);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_jingdian, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //获取实现接口的activity
        myListener = (Fragment_jingdian.MyListener) getActivity();//或者myListener=(MainActivity) context;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton button = getActivity().findViewById(R.id.shouqi_jinddian);
        RadioButton imageButton_zhongdananfang = getActivity().findViewById(R.id.imageButtou_zhongdananfang);
        RadioButton imageButton_huguangyan = getActivity().findViewById(R.id.imageButton_huguangyan);
        RadioButton imageButton_baiyunshan = getActivity().findViewById(R.id.imageButton_baiyunshan);
        imageButton_zhongdananfang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.sendValue_jingdian("中大南方");
            }
        });
        imageButton_huguangyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.sendValue_jingdian("湖光岩");
            }
        });
        imageButton_baiyunshan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.sendValue_jingdian("白云山");
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.hide(MainActivity.fragment_jingdian);
                ft.commit();
            }
        });
    }
}
