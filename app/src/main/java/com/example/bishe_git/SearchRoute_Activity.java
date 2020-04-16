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
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.SuggestionResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchRoute_Activity extends AppCompatActivity {
    String qidian, zhongdian;
    int jishu=0;
    ListView list;
    Search search;
    private EditText editText_qidian, editText_en;
    List<String> data = new ArrayList<String>();
    List<String> data_new = new ArrayList<String>();
    List<SuggestionResult.SuggestionInfo> resl_new;
    boolean loop = false;
    LatLng qidian_latLng = null, en_latLng = null;
    public static List<LatLng> latLngs;
    @SuppressLint("HandlerLeak")
    final Handler myHandler = new Handler() {
        @Override
        //重写handleMessage方法,根据msg中what的值判断是否执行后续操作
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                init1();
                search.resl=null;
                data = new ArrayList<String>();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_route);
        Init_Windows.init_WindowsTitle(SearchRoute_Activity.this);
        editText_qidian = findViewById(R.id.edit_qidian);
        editText_en = findViewById(R.id.edit_en);
        list = findViewById(R.id.listview);

        init_edit(editText_qidian);
        init_edit1(editText_en);
        //获取焦点，并弹出键盘
        Init_Windows.init_EditText(editText_qidian, SearchRoute_Activity.this);
        editText_Listener(editText_qidian);
        editText_Listener(editText_en);
    }

    private void init_edit(EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    search = new Search();
                    search.resl = null;
                    data = new ArrayList<String>();
                    loop = false;
                }
            }
        });
    }

    private void init_edit1(EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    search = new Search();
                    search.resl = null;
                    data = new ArrayList<String>();
                    loop = true;
                }
            }
        });
    }

    public void fanhui(View view) {
        Intent intent = new Intent(SearchRoute_Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void editText_Listener(EditText editText) {

        editText.addTextChangedListener(new TextWatcher() {
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

    public void init1() {
        for (int i = 0; i < search.resl.size(); i++) {
            data.add(search.resl.get(i).city + search.resl.get(i).district + search.resl.get(i).key);
        }
        data_new=data;
        resl_new =search.resl;
        //创建ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_expandable_list_item_1, data_new);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int ID = (int) id;
                String editText = data_new.get(ID);
                if (loop) {
                    editText_en.setText(editText);
                    qidian_latLng = resl_new.get(ID).getPt();
                } else {
                    editText_qidian.setText(editText);
                    en_latLng = resl_new.get(ID).getPt();
                }
            }
        });
    }

    public void addlist() {
        latLngs = new LinkedList<>();
        if (qidian_latLng != null && en_latLng != null) {
            latLngs.add(qidian_latLng);
            latLngs.add(en_latLng);
        }
    }

    public void buxing(View view) {
        addlist();
        if (latLngs.size() > 1) {
            Intent intent = new Intent(SearchRoute_Activity.this, MainActivity.class);
            intent.putExtra("daohan", 1);
            Bundle mBundle = new Bundle();

//        intent.putExtra(" en_latLng", en_latLng);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "起点或终点不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    public void qixing(View view) {
        addlist();
        if (latLngs.size() > 1) {
            Intent intent = new Intent(SearchRoute_Activity.this, MainActivity.class);
            intent.putExtra("daohan", 2);
//        intent.putExtra(" en_latLng", en_latLng);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "起点或终点不能为空", Toast.LENGTH_SHORT).show();
        }

    }

    public void jiache(View view) {
        addlist();
        if (latLngs.size() > 1) {
            Intent intent = new Intent(SearchRoute_Activity.this, MainActivity.class);
            intent.putExtra("daohan", 3);
//        intent.putExtra(" en_latLng", en_latLng);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "起点或终点不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    public void gongjiao(View view) {
        addlist();
        if (latLngs.size() > 1) {
            Intent intent = new Intent(SearchRoute_Activity.this, MainActivity.class);
            intent.putExtra("daohan", 4);
//        intent.putExtra(" en_latLng", en_latLng);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "起点或终点不能为空", Toast.LENGTH_SHORT).show();
        }

    }
}
