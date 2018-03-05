package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView imageViewLogo = null;
    private TextView textViewLibrary = null;
    static boolean hasStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("SAK", "SplashScreenActivity starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if(!hasStarted){
            Log.d("SAK", "Starting startupService...");
            startStartupService();
            hasStarted = true;
        }
    }

    private void startStartupService(){
        Thread thread = new Thread(new StartupService(this));
        thread.start();
    }
}

class StartupService implements Runnable{

    private Context context = null;

    StartupService(Context context){
        this.context = context;
    }

    @Override
    public void run() {
        try{
            int totalBooks = Integer.valueOf(context.getResources().getString
                    (R.string.home_fragment_total_new_arrivals));
            NewArrivalsFetcher newArrivalsFetcher = new NewArrivalsFetcher(context, totalBooks);
            Thread newArrivalFetcherThread = new Thread(newArrivalsFetcher);
            newArrivalFetcherThread.start();
            newArrivalFetcherThread.join();
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            ((AppCompatActivity)context).finish();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}