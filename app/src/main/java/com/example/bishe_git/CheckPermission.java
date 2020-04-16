package com.example.bishe_git;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CheckPermission {
    //需要获取的权限集合
    private static String[] permissionlist = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.VIBRATE,
            Manifest.permission.CAMERA
    };

    //需要打开的权限的操作方法
    public static void checkPermission(Activity activity) {
        boolean read = false, write = false, record = false, locatton = false,zhendong=false,camera=false;
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            read = true;
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            write = true;
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            record = true;
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            locatton = true;
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            camera = true;
        }
        if (ContextCompat.checkSelfPermission(activity,Manifest.permission.VIBRATE)
                != PackageManager.PERMISSION_GRANTED) {
            zhendong = true;
        }
        if (read && write && record&&zhendong || locatton||camera) {
            ActivityCompat.requestPermissions(activity, permissionlist, 0x01);
        }

    }
}


