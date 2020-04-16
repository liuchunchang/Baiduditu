package com.example.bishe_git;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class AddJson {
    public Bitmap bitmap;
    public JSONArray jingdian;
    public JSONObject jingdian_JSONObject;
    public JSONObject json;
    public String name, xiangqing;
    AddPoint addPoint=new AddPoint();
    AddJson(String json_array){
        try {
            InputStream is = getClass().getClassLoader().
                    getResourceAsStream("assets/" + "jingdian.json");
            InputStreamReader streamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            is.close();
            try {
                json = new JSONObject(stringBuilder.toString());
                jingdian = json.getJSONArray(json_array);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   AddJson(String json_name, String json_array) {
       try {
           InputStream is = getClass().getClassLoader().
                   getResourceAsStream("assets/" + json_name);
           InputStreamReader streamReader = new InputStreamReader(is);
           BufferedReader reader = new BufferedReader(streamReader);
           String line;
           StringBuilder stringBuilder = new StringBuilder();
           while ((line = reader.readLine()) != null) {
               stringBuilder.append(line);
           }
           reader.close();
           is.close();
           try {
               json = new JSONObject(stringBuilder.toString());
               jingdian = json.getJSONArray(json_array);
               //往地图上添加批量的覆盖点
               for (int i = 0; i <jingdian.length(); i++) {
                   try {
                       int id = jingdian.getJSONObject(i).getInt("id");
                       double weidu =jingdian.getJSONObject(i).getDouble("Latitude");
                       double jingdu =jingdian.getJSONObject(i).getDouble("Longitude");
                      addPoint.addPoint(weidu, jingdu, id);
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }
           } catch (JSONException e) {
               e.printStackTrace();
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
    AddJson(String json_name, String json_array, int json_id) {
        try {
            InputStream is = getClass().getClassLoader().
                    getResourceAsStream("assets/" + json_name);
            InputStreamReader streamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            is.close();
            try {
                json = new JSONObject(stringBuilder.toString());
                jingdian = json.getJSONArray(json_array);
                jingdian_JSONObject = jingdian.getJSONObject(json_id);
                bitmap = BitmapFactory.decodeStream(getClass().getResourceAsStream(jingdian_JSONObject.getString("image")));
                name = jingdian_JSONObject.getString("name");
                xiangqing = jingdian_JSONObject.getString("xiangqing");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
