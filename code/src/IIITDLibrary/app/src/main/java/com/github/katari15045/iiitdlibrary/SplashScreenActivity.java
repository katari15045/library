package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
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
        }
    }

    private void startStartupService(){
        StartupService startupService = new StartupService(this);
        startupService.execute();
    }
}

class StartupService extends AsyncTask<Void, Void, Void>{

    private Context context = null;

    @Override
    protected void onPostExecute(Void aVoid) {
        if(!Database.isConnected()){
            SplashScreenActivity.hasStarted = false;
            buildAlertDialog();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
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

    StartupService(Context context){
        this.context = context;
    }

    private void buildAlertDialog(){
        Log.d("SAK", "Building alert dialog can't connect...");
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = ((AppCompatActivity)context).getLayoutInflater().inflate
                (R.layout.alert_dialog_cant_connect, null);
        builder.setView(view);
        TextView textView = view.findViewById(R.id.alert_dialog_cant_connect_text_view_title);
        textView.setText(R.string.alert_dialog_cant_connect_text_view_title_db);
        Button button = view.findViewById(R.id.alert_dialog_cant_connect_button);
        button.setOnClickListener(new DialogButListener(context));
        builder.setCancelable(false);
        builder.show();
    }
}

class DialogButListener implements View.OnClickListener{

    private Context context = null;

    DialogButListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, SplashScreenActivity.class);
        context.startActivity(intent);
        ((AppCompatActivity)context).finish();
    }
}