package com.github.katari15045.bookimagefromdb;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Saketh Katari on 04-03-2018.
 */

public class WebAPI implements Runnable {
    private URL url = null;
    private String dataFetched = null;

    WebAPI(String urlStr){
        try{
            url = new URL(urlStr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            String line = null;
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(urlConnection.getInputStream()));
            do{
                line = bufferedReader.readLine();
                if(line == null){
                    break;
                }
                stringBuilder.append(line);
            }while(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        dataFetched = stringBuilder.toString();
    }

    String getDataFetched(){
        return  dataFetched;
    }
}
