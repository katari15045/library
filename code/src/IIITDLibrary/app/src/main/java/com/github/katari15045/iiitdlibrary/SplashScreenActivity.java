package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView imageViewLogo = null;
    private TextView textViewLibrary = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        startWaiter();
    }

    private void startWaiter(){
        Thread thread = new Thread(new Waiter(this));
        thread.start();
    }
}

class Waiter implements Runnable{

    private Context context = null;

    Waiter(Context context){
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