package com.github.katari15045.iiitdlibrary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AskForDischargeActivity extends AppCompatActivity {

    private Toolbar mActionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_discharge);

        mActionBarToolbar = (Toolbar) findViewById(R.id.fineToolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("  Discharge");

        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setIcon(R.drawable.ic_exit_to_app_white_36dp);
        mActionBarToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));



        Button dischargeButton = (Button) findViewById(R.id.discharge_submit_button);
        dischargeButton.setOnClickListener(new View.OnClickListener()
        {
            AlertDialog.Builder dialogBox;
            @Override
            public void onClick(View view)
            {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    dialogBox = new AlertDialog.Builder(AskForDischargeActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                }
                else
                {
                    dialogBox = new AlertDialog.Builder(AskForDischargeActivity.this);
                }

                dialogBox.setTitle("Ask for a discharge")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(AskForDischargeActivity.this, "Request submitted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
            }
        });

    }
}
