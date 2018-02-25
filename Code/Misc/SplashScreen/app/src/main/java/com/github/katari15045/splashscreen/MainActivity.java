package com.github.katari15045.splashscreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewLogo = null;
    private TextView textViewLibrary = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideActionBar();
        startAnimation();
        startWaiter();
    }

    private void startAnimation(){
        collectViews();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_screen);
        imageViewLogo.startAnimation(animation);
        textViewLibrary.startAnimation(animation);
    }

    private void collectViews(){
        imageViewLogo = (ImageView) findViewById(R.id.activity_main_image_view_logo);
        textViewLibrary = (TextView) findViewById(R.id.activity_main_text_view_library);
    }

    private void startWaiter(){
        Thread thread = new Thread(new Waiter(this));
        thread.start();
    }

    private void hideActionBar(){
        getSupportActionBar().hide();
    }
}

class Waiter implements Runnable{

    private Context context = null;
    private int sleepTime = 5000;

    public Waiter(Context context){
        this.context = context;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(sleepTime);
        } catch (InterruptedException e){
          e.printStackTrace();
        }finally {
            Intent intent = new Intent(context, HomeActivity.class);
            context.startActivity(intent);
            ((AppCompatActivity)context).finish();
        }
    }
}