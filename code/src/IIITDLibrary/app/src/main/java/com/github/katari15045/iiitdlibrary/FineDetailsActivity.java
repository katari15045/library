package com.github.katari15045.iiitdlibrary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.github.katari15045.iiitdlibrary.Adapter.FineAdapter;
import com.github.katari15045.iiitdlibrary.Util.FineUtil;
import com.github.katari15045.iiitdlibrary.Util.UsernameUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FineDetailsActivity extends AppCompatActivity {


    private ArrayList<FineUtil> fineList;
    private FineAdapter adapterFineDetails;
    private ResultSet fineResultSet;
    private TextView totalFine;

    private Toolbar mActionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout_fine);


        mActionBarToolbar = (Toolbar) findViewById(R.id.fineToolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("FINE");

        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        totalFine = (TextView) findViewById(R.id.totalFineDetails);

        //TODO -- could be a potential bug -- remove extracting data from intent

        totalFine.setText("Total fine = " + getIntent().getStringExtra("totalFine"));

        ListView listView = (ListView) findViewById(R.id.fine_listView);
        fineList = new ArrayList<>();

        fineList.add(new FineUtil("Date", "Description", "Fine", "Amount Outstanding"));
        adapterFineDetails = new FineAdapter(this, fineList);

        Log.i(FineDetailsActivity.this + "" , "*************************+ " + UsernameUtil.username);
        //TODO -- database access
        FineDetailsAsynTask fineDetailsAsynTask = new FineDetailsAsynTask();
        fineDetailsAsynTask.execute(UsernameUtil.username);


        listView.setAdapter(adapterFineDetails);

    }


    private class FineDetailsAsynTask extends AsyncTask<String, Void,Void>
    {
        Database database;
        @Override
        protected Void doInBackground(String... ids) {

            String command = "select amountoutstanding, description, date from accountlines where borrowernumber in (select borrowernumber from borrowers where userid =\"" + ids[0] + "\");";
            database = new Database(command, true);
            Thread dbThread = new Thread(database);
            dbThread.start();
            try {
                dbThread.join();
                fineResultSet = database.getResultSet();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            String fineTotal = "";
            if(fineResultSet != null) {
                try {
                    while (fineResultSet.next()) {
                        Log.i(this + "", fineResultSet.getString("description") + " ---------------  ");

                        String date = fineResultSet.getString("date");
                        String description = fineResultSet.getString("description");
                        String amountOutStanding = fineResultSet.getString("amountoutstanding").substring(0, 2);

                        Log.i("" + this, "***************** " + date);
                        Log.i("" + this, "***************** " + description);
                        Log.i("" + this, "***************** " + amountOutStanding);


                        if(description.length() >= 16) {
                            description = description.substring(0, description.length() - 16);
                        }
                        FineUtil fineUtil = new FineUtil(date, description, amountOutStanding, amountOutStanding);
                        fineList.add(fineUtil);

                    }
                    adapterFineDetails.notifyDataSetChanged();


                    Log.i("" + this, "***************** ");


                } catch (SQLException e) {
                    Log.i(this + "", "FUCK -------------------");
                }
            }

            database.close();


        }
    }

}
