package com.example.bishe_git;

import android.content.Context;
import android.view.WindowManager;

import static com.baidu.mapapi.BMapManager.getContext;

public class Windows_size {
    public int windows_width,windows_height;
    Windows_size(){
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        windows_width= wm.getDefaultDisplay().getWidth();
        windows_height = wm.getDefaultDisplay().getHeight();
    }
}
