package com.bawei.zhangboyi;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * date:2019/11/26
 * author:张博一(zhangboyi)
 * function:封装类
 */
public class NetUtile {

    //加新
    private static NetUtile netUtile = new NetUtile();

    //单例
    public static NetUtile getInstance() {
        return netUtile;
    }

    //单例
    private NetUtile(){

    }

    //流转jso
    public String io2String(InputStream inputStream){
        byte[] bytes = new byte[1024];

        int len = -1;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String json= "";

        try {
            while ((len = inputStream.read(bytes)) != -1){
                byteArrayOutputStream.write(bytes,0,len);
            }

            json = new String(byteArrayOutputStream.toByteArray());
        }catch (IOException i){
            i.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
         return json;
    }

    //流转图片
    public Bitmap io2bitmap(InputStream inputStream){
        return BitmapFactory.decodeStream(inputStream);
    }

    //请求json
    @SuppressLint("StaticFieldLeak")
    public void doGet(String httpurl, final MyCallBack myCallBack){
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {

                myCallBack.ondoGetSuccsess(s);
            }

            @Override
            protected String doInBackground(String... strings) {
                String json = "";
                HttpURLConnection httpURLConnection = null;
                InputStream inputStream = null;
                try {
                    URL url = new URL(strings[0]);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    //请求
                    httpURLConnection.setRequestMethod("GET");
                    //请求超时
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    //开始
                    httpURLConnection.connect();
                    //判断
                    if(httpURLConnection.getResponseCode() == 200){
                        json  = io2String(httpURLConnection.getInputStream());
                        Log.i("json",json.toString());
                    }else {
                        Log.i("json","请求失败");
                    }
                }catch (IOException i){
                    i.printStackTrace();
                }finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
                return json;
            }
        }.execute(httpurl);
    }

    //请求bitmap
    @SuppressLint("StaticFieldLeak")
    public void doGetPhoto(String httpurl, final MyCallBack myCallBack){
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {

                myCallBack.ondoGetPhotoSuccsess(bitmap);
            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                Bitmap bitmap = null;
                HttpURLConnection httpURLConnection = null;
                InputStream inputStream = null;
                try {
                    URL url = new URL(strings[0]);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    //请求
                    httpURLConnection.setRequestMethod("GET");
                    //请求超时
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    //开始
                    httpURLConnection.connect();
                    //判断
                    if(httpURLConnection.getResponseCode() == 200){
                        bitmap = io2bitmap(httpURLConnection.getInputStream());
                        Log.i("bitmap",bitmap.toString());
                    }else {
                        Log.i("bitmap","请求失败");
                    }
                }catch (IOException i){
                    i.printStackTrace();
                }finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
                return bitmap;
            }
        }.execute(httpurl);
    }

    //调用接口
    public interface MyCallBack{
        void ondoGetSuccsess(String json);

        void ondoGetPhotoSuccsess(Bitmap bitmap);
    }

}
