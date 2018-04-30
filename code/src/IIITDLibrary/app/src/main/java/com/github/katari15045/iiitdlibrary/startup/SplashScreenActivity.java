package com.github.katari15045.iiitdlibrary.startup;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.home.HomeActivity;
import com.github.katari15045.iiitdlibrary.misc.Database;

public class SplashScreenActivity extends AppCompatActivity {

    public static boolean hasStarted = false;
    private String debugTag = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        debugTag = getString(R.string.debug_tag);
        Log.d(debugTag, "SplashScreenActivity starts");
        if(!hasStarted){
            Log.d(debugTag, "Starting startupService...");
            startStartupService();
        }
    }

    private void startStartupService(){
        StartupService startupService = new StartupService(this);
        startupService.execute();
    }

    @Override
    public void onBackPressed() {
        Log.d(debugTag, "SplashScreen::BackButton");
        Toast.makeText(this, getResources().getString(R.string.loading),
                Toast.LENGTH_SHORT).show();
    }
}

class StartupService extends AsyncTask<Void, Void, Void>{

    private int totalBooks = -1;
    private Context context = null;

    public StartupService(Context context){
        this.context = context;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(!Database.isConnected() || NewArrivalsFetcher.getCards().size() < totalBooks){
            SplashScreenActivity.hasStarted = false;
            MyAlertDialog.build(context, SplashScreenActivity.class);
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            totalBooks = Integer.valueOf(context.getResources().getString
                    (R.string.total_new_arrivals));
            NewArrivalsFetcher newArrivalsFetcher = new NewArrivalsFetcher(context, totalBooks);
            Thread newArrivalFetcherThread = new Thread(newArrivalsFetcher);
            newArrivalFetcherThread.start();
            newArrivalFetcherThread.join();
            if(!Database.isConnected() || NewArrivalsFetcher.getCards().size() < totalBooks){
                return null;
            }
            SplashScreenActivity.hasStarted = true;
            Intent intent = new Intent(context, HomeActivity.class);
            context.startActivity(intent);
            ((AppCompatActivity)context).finish();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}