package com.github.katari15045.iiitdlibrary.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.katari15045.iiitdlibrary.Gui.MyAlertDialog;
import com.github.katari15045.iiitdlibrary.Helper.Database;
import com.github.katari15045.iiitdlibrary.Helper.Global;
import com.github.katari15045.iiitdlibrary.Helper.NewArrivalsFetcher;
import com.github.katari15045.iiitdlibrary.R;

public class SplashScreenActivity extends AppCompatActivity {

    public static boolean hasStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("SAK", "SplashScreenActivity starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Global.context = this;
        if(!hasStarted){
            Log.d("SAK", "Starting startupService...");
            startStartupService();
        }
    }

    private void startStartupService(){
        StartupService startupService = new StartupService();
        startupService.execute();
    }
}

class StartupService extends AsyncTask<Void, Void, Void>{

    @Override
    protected void onPostExecute(Void aVoid) {
        if(!Database.isConnected()){
            SplashScreenActivity.hasStarted = false;
            MyAlertDialog.build(SplashScreenActivity.class);
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Context context = Global.context;
        try{
            int totalBooks = Integer.valueOf(context.getResources().getString
                    (R.string.home_fragment_total_new_arrivals));
            NewArrivalsFetcher newArrivalsFetcher = new NewArrivalsFetcher(context, totalBooks);
            Thread newArrivalFetcherThread = new Thread(newArrivalsFetcher);
            newArrivalFetcherThread.start();
            newArrivalFetcherThread.join();
            if(!Database.isConnected()){
                return null;
            }
            SplashScreenActivity.hasStarted = true;
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            ((AppCompatActivity)context).finish();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}