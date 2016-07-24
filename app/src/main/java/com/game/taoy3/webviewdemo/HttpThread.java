package com.game.taoy3.webviewdemo;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by taoy3 on 16/3/17.
 */
public class HttpThread extends Thread{
    private String url;
    public HttpThread(String url){
        this.url = url;
    }
    @Override
    public void run() {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            InputStream inputStream = connection.getInputStream();
            File downFile;
            File sdfFile;
            FileOutputStream out = null;
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                downFile = Environment.getExternalStorageDirectory();
                sdfFile = new File(downFile,"test.apk");
                out = new FileOutputStream(sdfFile);
            }
            byte[] b = new byte[6*1024];
            int len;
            while ((len = inputStream.read())!=-1){
                if(out!=null){
                    out.write(b,0,len);
                }
            }
            if(inputStream!=null){
                inputStream.close();
            }
            if(out !=null){
                out.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
