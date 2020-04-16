package com.example.bishe_git;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;

import android.os.Message;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class TianQi_Activity extends AppCompatActivity {
    //    public TextView textView;
    public EditText editText;
    public TextView Text_city, Text_week, Text_wea, Text_tem, Text_tem1, Text_update_tiem, Text_air_level, Text_air_tips;
    public ImageView Image_wea_img;
    public String address_name;
    public JSONObject json_tianqi;
    int jishu = 0;
    String city, wea, tem, update_tiem, week, wea_img, tem1, tem2, air_level, air_tips;

    @SuppressLint("HandlerLeak")
    final Handler myHandler = new Handler() {
        @Override
        //重写handleMessage方法,根据msg中what的值判断是否执行后续操作
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                set_init();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tian_qi);
        init();
        Init_Windows.init_WindowsTitle(TianQi_Activity.this);
        Init_Windows.init_EditText(editText, TianQi_Activity.this);
    }

    private void init() {
        editText = findViewById(R.id.edit_tianqi);
        Text_city = findViewById(R.id.city);
        Text_week = findViewById(R.id.week);
        Text_update_tiem = findViewById(R.id.uptime);
        Image_wea_img = findViewById(R.id.wea_img);
        Text_wea = findViewById(R.id.wea);
        Text_tem = findViewById(R.id.tem);
        Text_air_level = findViewById(R.id.air_level);
        Text_air_tips = findViewById(R.id.air_tips);
        Text_tem1 = findViewById(R.id.tem1);
        Intent intent = getIntent();
       address_name = intent.getStringExtra("city");
        if(address_name!=null){
            update_tianqi();
        }else{
            address_name="北京";
            update_tianqi();
        }
    }

    public void fanhui_tianqi(View view) {
        Intent intent = new Intent(TianQi_Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void update_tianqi() {
        if (address_name != null) {
            final HttpUtils httpUtils = new HttpUtils();
            httpUtils.SendGetRequest(address_name);
            final Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                 jishu++;
                    if (httpUtils.success) {
                        json_tianqi = httpUtils.json_tianqi;
                        try {
                            city = json_tianqi.getString("city");
                            wea = json_tianqi.getString("wea");
                            tem = json_tianqi.getString("tem");
                            update_tiem = json_tianqi.getString("update_time");
                            week = json_tianqi.getString("week");
                            wea_img = json_tianqi.getString("wea_img");
                            tem1 = json_tianqi.getString("tem1");
                            tem2 = json_tianqi.getString("tem2");
                            air_level = json_tianqi.getString("air_level");
                            air_tips = json_tianqi.getString("air_tips");
                            httpUtils.success = false;
                            myHandler.sendEmptyMessage(0x123);
                            timer.cancel();
                            jishu=0;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    if(jishu>50){
                        timer.cancel();
                        jishu=0;
                    }
//timer.cancel(); 终止此计时器，丢弃所有当前已安排的任务。这不会干扰当前正在执行的任务（如果存在）。一旦终止了计时器，那么它的执行线程也会终止，并且无法根据它安排更多的任务。注意，在此计时器调用的计时器任务的 run 方法内调用此方法，就可以绝对确保正在执行的任务是此计时器所执行的最后一个任务。
                }
            };

            timer.schedule(task, 50, 50); //这个命令就是0.05秒钟之后执行TimerTask里边的内容，后边的执行时间间隔为0.05秒钟。
        }
    }

    public void chaxun_tianqi(View view) {
        address_name = editText.getText().toString().trim();
        update_tianqi();
    }

    private void set_init() {
        Text_city.setText(city);
        Text_week.setText(week);
        Text_update_tiem.setText(update_tiem);
        Text_wea.setText(wea);
        Text_tem.setText(tem + "度");
        Text_tem1.setText(tem2 + "度" + "~" + tem1 + "度");
        Text_air_level.setText("空气质量：" + air_level);
        Text_air_tips.setText(air_tips);
        Bitmap bitmap = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/" + wea_img + ".png"));
        Image_wea_img.setImageBitmap(bitmap);
    }
}
