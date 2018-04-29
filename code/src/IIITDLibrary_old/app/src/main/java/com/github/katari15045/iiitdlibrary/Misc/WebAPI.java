package com.github.katari15045.iiitdlibrary.Misc;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Saketh Katari on 04-03-2018.
 */

public class WebAPI implements Runnable {
    private URL url = null;
    private String dataFetched = null;

    public WebAPI(String urlStr){
        try{
            url = new URL(urlStr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        StringBuilder stringBuilder = new StringBuilder();
        URLConnection urlConnection = null;
        try
        {
            String line = null;
            Log.d("SAK","Connecting to Google API...");
            urlConnection = url.openConnection();
            Log.d("SAK","Opening Input Stream...");
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(urlConnection.getInputStream()));
            do{
                Log.d("SAK","Reading a line...");
                line = bufferedReader.readLine();
                Log.d("SAK","Got a line");
                if(line == null){
                    break;
                }
                stringBuilder.append(line);
            }while(true);
        }catch (Exception e){
            Log.d("SAK", "Can't connect to the Web API.");
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            try{
                Log.d("SAK",  httpConnection.getResponseCode() + " -> "
                        + httpConnection.getResponseMessage());
            }catch (Exception e2){
                e2.printStackTrace();
            }
            e.printStackTrace();
        }
        dataFetched = stringBuilder.toString();
    }

    public String getDataFetched(){
        return  dataFetched;
    }
}
