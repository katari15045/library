package com.github.katari15045.iiitdlibrary;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MessagingActivity extends AppCompatActivity {


    private CheckBox checkBox_itemDue;
    private CheckBox checkBox_advancenotice;
    private CheckBox checkBox_holdfilled;
    private CheckBox checkBox_itemcheckin;
    private CheckBox checkBox_itemcheckout;

    private Spinner messagingSpinner;
    private Toolbar mActionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        mActionBarToolbar = (Toolbar) findViewById(R.id.fineToolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("  Messaging");

        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_message_white_36dp);
        mActionBarToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));



        messagingSpinner = (Spinner) findViewById(R.id.fragment_messaging_spinner);
        ArrayList<String> spinnerList = new ArrayList<>();
        for(int i = 1; i < 15; i++)
        {
            spinnerList.add(""+i);
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        messagingSpinner.setAdapter(spinnerAdapter);

        checkBox_itemDue = (CheckBox) findViewById(R.id.messaging_itemdue_checkbox);
        checkBox_advancenotice = (CheckBox) findViewById(R.id.messaging_advancenotice_checkbox);
        checkBox_holdfilled = (CheckBox) findViewById(R.id.messaging_holdfilled_checkbox);
        checkBox_itemcheckin = (CheckBox) findViewById(R.id.messaging_itemcheckin_checkbox);
        checkBox_itemcheckout = (CheckBox) findViewById(R.id.messaging_itemcheckout_checkbox);

        checkBox_itemDue.setChecked(true);
        checkBox_advancenotice.setChecked(true);
        checkBox_holdfilled.setChecked(true);
        checkBox_itemcheckin.setChecked(true);
        checkBox_itemcheckout.setChecked(true);

        Button submitButton = (Button) findViewById(R.id.messaging_submit_button);
        Button cancelButton = (Button) findViewById(R.id.messaging_cancel_button);

        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Toast.makeText(MessagingActivity.this, "Changes submitted", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                Toast.makeText(MessagingActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
