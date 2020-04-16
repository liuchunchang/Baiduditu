package com.example.bishe_git;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.SuggestionResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchAddress_Activity extends AppCompatActivity {
    String keyword;
    ListView list_test;
    int jishu=0;
    private EditText editText_keyword;
    List<String> data = new ArrayList<String>();
    List<SuggestionResult.SuggestionInfo> resl_new;
    Search search = new Search();
    @SuppressLint("HandlerLeak")
    final Handler myHandler = new Handler() {
        @Override
        //重写handleMessage方法,根据msg中what的值判断是否执行后续操作
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                init1();
                search.resl=null;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_address);
        editText_keyword = findViewById(R.id.edit_keyword);
        //获取焦点，并弹出键盘
        Init_Windows.init_EditText(editText_keyword, SearchAddress_Activity.this);
        //获取ListView对象，通过调用setAdapter方法为ListView设置Adapter设置适配器
        list_test = findViewById(R.id.listview);
        editText_Listener();
        Init_Windows.init_WindowsTitle(SearchAddress_Activity.this);
    }


    private void editText_Listener() {

        editText_keyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                //s:变化后的所有字符
//                Toast.makeText(SearchActivity.this, "变化2:" + S + ";" + start + ";" + before + ";" + count, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                //s:变化前的所有字符； start:字符开始的位置； count:变化前的总字节数；after:变化后的字节数
//                Toast.makeText(SearchActivity.this, "变化前1:" + s + ";" + start + ";" + count + ";" + after, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //S：变化后的所有字符；start：字符起始的位置；before: 变化之前的总字节数；count:变化后的字节数
//                Toast.makeText(SearchActivity.this, "变化后3:" + s + ";", Toast.LENGTH_SHORT).show();
                String S = s.toString();
                if (S != null) {
                    search.search(S);
                    final Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        public void run() {
                            jishu++;
                            if (search.resl != null) {
                                myHandler.sendEmptyMessage(0x123);
                                timer.cancel();
                                jishu=0;
                            }
                            if (jishu>50){
                                timer.cancel();
                                jishu=0;
                            }
//timer.cancel(); 终止此计时器，丢弃所有当前已安排的任务。这不会干扰当前正在执行的任务（如果存在）。一旦终止了计时器，那么它的执行线程也会终止，并且无法根据它安排更多的任务。注意，在此计时器调用的计时器任务的 run 方法内调用此方法，就可以绝对确保正在执行的任务是此计时器所执行的最后一个任务。
                        }
                    };
                    timer.schedule(task, 50, 50); //这个命令就是0.05秒钟之后执行TimerTask里边的内容，后边的执行时间间隔为0.05秒钟。
                }
            }
        });
    }

    public void chaxun(View view) {
        keyword = editText_keyword.getText().toString().trim();
        search.search(keyword);
        if (search.resl != null) {
            init1();
        }
    }

    public void init1() {
        for (int i = 0; i < search.resl.size(); i++) {
            data.add(search.resl.get(i).city + search.resl.get(i).district + search.resl.get(i).key);
        }
        //创建ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_expandable_list_item_1, data);
        list_test.setAdapter(adapter);
       resl_new=search.resl;
        //给ListView一个默认选项
//        list_test.setItemChecked(0, true);
        list_test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int ID = (int) id;
                Intent intent = new Intent(SearchAddress_Activity.this, MainActivity.class);
                intent.putExtra("id", ID);
                LatLng latLng = resl_new.get(ID).getPt();
                intent.putExtra("latitude_address", latLng.latitude);
                intent.putExtra("longitude_address",  latLng.longitude);
                startActivity(intent);
                finish();
            }
        });
        data = new ArrayList<String>();
    }

    //返回按钮
    public void fanhui(View view) {
        Intent intent = new Intent(SearchAddress_Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
