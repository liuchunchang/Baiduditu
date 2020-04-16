package com.example.bishe_git;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Jingdian_Activity extends AppCompatActivity {
    int id;
    String json_name, json_array;
    private ImageView imageView;
    private TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        Init_Windows.init_WindowsTitle(Jingdian_Activity.this);
    }

    private void init() {
        // 首先获取到意图对象
        Intent intent = getIntent();
        // 获取到传递过来的id
        id = intent.getIntExtra("id", 0);
        json_name = intent.getStringExtra("json_name");
        json_array = intent.getStringExtra("json_array");
        setContentView(R.layout.activity_jingdian);
        //图片
        imageView = findViewById(R.id.JingDianImage);
        //景点
        textView1 = findViewById(R.id.JingDian);
        //景点详情
        textView2 = findViewById(R.id.JingDianText);
        AddJson addJson = new AddJson(json_name, json_array, id);
        imageView.setImageBitmap(addJson.bitmap);
        textView1.setText(addJson.name);
        textView2.setText(addJson.xiangqing);
    }

    public void fanhui(View view) {
        Intent intent = new Intent(Jingdian_Activity.this, MainActivity.class);
        intent.putExtra("jingdian_id", id);
        intent.putExtra("jingdian",  json_array);
        startActivity(intent);
        finish();
    }
}
