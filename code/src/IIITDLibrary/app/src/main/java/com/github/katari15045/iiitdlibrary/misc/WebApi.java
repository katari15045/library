package com.github.katari15045.iiitdlibrary.misc;


import android.content.Context;
import android.util.Log;

import com.github.katari15045.iiitdlibrary.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Saketh Katari on 04-03-2018.
 */

public class WebApi implements Runnable {
    private Context context = null;
    private URL url = null;
    private String debugTag = null;
    private String dataFetched = null;

    public WebApi(Context context, String urlStr){
        this.context = context;
        debug();
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
            Log.d(debugTag,"Connecting to Google API...");
            urlConnection = url.openConnection();
            Log.d(debugTag,"Opening Input Stream...");
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(urlConnection.getInputStream()));
            do{
                Log.d(debugTag,"Reading a line...");
                line = bufferedReader.readLine();
                Log.d(debugTag,"Got a line");
                if(line == null){
                    break;
                }
                stringBuilder.append(line);
            }while(true);
        }catch (Exception e){
            Log.d(debugTag, "Can't connect to the Web API.");
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            try{
                Log.d(debugTag,  httpConnection.getResponseCode() + " -> "
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

    private void debug(){
        debugTag = context.getResources().getString(R.string.debug_tag);
        Log.d(debugTag, "WebApi::fetching image...");
    }
}