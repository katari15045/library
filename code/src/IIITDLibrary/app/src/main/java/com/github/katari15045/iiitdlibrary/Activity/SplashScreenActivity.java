package com.github.katari15045.iiitdlibrary.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

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

    @Override
    public void onBackPressed() {
        Log.d("SAK", "SplashScreen::BackButton");
        Toast.makeText(this, getResources().getString(R.string.please_wait),
                Toast.LENGTH_SHORT).show();
    }
}

class StartupService extends AsyncTask<Void, Void, Void>{

    private int totalBooks = -1;

    @Override
    protected void onPostExecute(Void aVoid) {
        if(!Database.isConnected() || NewArrivalsFetcher.getCards().size() < totalBooks){
            SplashScreenActivity.hasStarted = false;
            MyAlertDialog.build(SplashScreenActivity.class);
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Context context = Global.context;
        try{
            totalBooks = Integer.valueOf(context.getResources().getString
                    (R.string.home_fragment_total_new_arrivals));
            NewArrivalsFetcher newArrivalsFetcher = new NewArrivalsFetcher(context, totalBooks);
            Thread newArrivalFetcherThread = new Thread(newArrivalsFetcher);
            newArrivalFetcherThread.start();
            newArrivalFetcherThread.join();
            if(!Database.isConnected() || NewArrivalsFetcher.getCards().size() < totalBooks){
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