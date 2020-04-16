package com.example.bishe_git;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpUtils {
    public JSONObject json_tianqi;//定义接受json数据信息的变量
    public boolean success = false;
   public String acceptData;

    //发送天气请求，在其他地方进行调用
    public void SendGetRequest(final String content) {
        new Thread() {
            @Override
            public void run() {
                //例如String pathString =  "http://wthrcdn.etouch.cn/weather_mini?city="+"北京";  //请求天气信息
                String pathString = "https://tianqiapi.com/api?version=v6&appid=14296324&appsecret=Vtgc02Cy&city=" + content;
                HttpURLConnection connection;
                try {
                    URL url = new URL(pathString);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    //接受数据
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                        String line;
                        while ((line = bufferedReader.readLine()) != null) { //不为空进行操作
                            acceptData += line;
                        }
                        acceptData = decode(acceptData).substring(4);
                        success = true;
                        json_tianqi = new JSONObject(acceptData);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }

}
