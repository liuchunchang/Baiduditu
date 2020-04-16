package com.example.bishe_git;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {
    int jishu = 0;
    private MyListener myListener;
    ImageView imageButton_reli;

    //定义回调接口
    public interface MyListener {
        public void sendValue(String value);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //获取实现接口的activity
        myListener = (MyFragment.MyListener) getActivity();//或者myListener=(MainActivity) context;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton button = getActivity().findViewById(R.id.shouqi);
        RadioButton imageButton_biaozhun = getActivity().findViewById(R.id.imageButton_biaozhun);
        imageButton_biaozhun.setChecked(true);
        RadioButton imageButton_weixing = getActivity().findViewById(R.id.imageButton_weixing);
        imageButton_reli = getActivity().findViewById(R.id.imageButton_reli);
        Bitmap bitmap = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/relitu.png"));
        imageButton_reli.setImageBitmap(bitmap);
        imageButton_reli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jishu++;
                if (jishu % 2 == 1) {
                    Bitmap bitmap = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/reli_one.png"));
                    imageButton_reli.setImageBitmap(bitmap);
                    myListener.sendValue("热力开");
                } else {
                    Bitmap bitmap = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/relitu.png"));
                    imageButton_reli.setImageBitmap(bitmap);
                    myListener.sendValue("热力关");
                }
            }
        });
        imageButton_weixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.sendValue("卫星");
            }
        });
        imageButton_biaozhun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.sendValue("标准");
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.hide(MainActivity.myFragment);
                ft.commit();
            }
        });
    }


}
