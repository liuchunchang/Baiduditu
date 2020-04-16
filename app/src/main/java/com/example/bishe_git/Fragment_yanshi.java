package com.example.bishe_git;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_yanshi extends Fragment {
    private Fragment_yanshi.MyListener myListener;

    //定义回调接口
    public interface MyListener {
        public void sendValue_yanshi(String value);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //获取实现接口的activity
        myListener =  (Fragment_yanshi.MyListener)getActivity();//或者myListener=(MainActivity) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_yanshi, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton button = getActivity().findViewById(R.id.shouqi_yanshi);
        RadioButton imageButton_guanbi=getActivity().findViewById(R.id.imageButton_guanbi);
        imageButton_guanbi.setChecked(true);
        RadioButton imageButton_gray=getActivity().findViewById(R.id.imageButton_gray);
        RadioButton imageButton_cha=getActivity().findViewById(R.id.imageButton_cha);
        RadioButton imageButton_balck=getActivity().findViewById(R.id.imageButton_balck);
        RadioButton imageButton_chuxing=getActivity().findViewById(R.id.imageButton_chuxing);
        RadioButton imageButton_wuliu=getActivity().findViewById(R.id.imageButton_wuliu);
        imageButton_guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.sendValue_yanshi("标准");
            }
        });
        imageButton_gray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.sendValue_yanshi("灰色");
            }
        });
        imageButton_cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.sendValue_yanshi("茶色");
            }
        });
        imageButton_balck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.sendValue_yanshi("黑色");
            }
        });
        imageButton_chuxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.sendValue_yanshi("出行");
            }
        });
        imageButton_wuliu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.sendValue_yanshi("物流");
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.hide(MainActivity.fragment_yanshi);
                ft.commit();
            }
        });
    }

}
