package com.github.katari15045.iiitdlibrary.startup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.katari15045.iiitdlibrary.R;

/**
 * Created by Saketh Katari on 14-03-2018.
 */

public class MyAlertDialog {

    public static void build(Context context, Object targetActivity){
        Log.d("SAK", "Building alert dialog can't connect...");
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = ((AppCompatActivity)context).getLayoutInflater().inflate
                (R.layout.alert_dialog_cant_connect, null);
        builder.setView(view);
        TextView textViewContent = view.findViewById(R.id.alert_dialog_cant_connect_text_view_title);
        textViewContent.setText(R.string.alert_dialog_cant_connect_text_view_title_db);
        TextView textViewRetry = view.findViewById(R.id.alert_dialog_cant_connect_text_view_retry);
        textViewRetry.setOnClickListener(new DialogButListener(context, targetActivity));
        builder.setCancelable(false);
        builder.show();
    }
}

class DialogButListener implements View.OnClickListener{

    private Context context = null;
    private Object targetActivity = null;

    DialogButListener(Context context, Object targetActivity){
        this.context = context;
        this.targetActivity = targetActivity;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, (Class<AppCompatActivity>) targetActivity);
        context.startActivity(intent);
        ((AppCompatActivity)context).finish();
    }
}