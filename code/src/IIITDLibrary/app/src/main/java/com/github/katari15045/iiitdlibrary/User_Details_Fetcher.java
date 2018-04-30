package com.github.katari15045.iiitdlibrary;

/**
 * Created by Sunny on 05/03/18.
 */

import android.app.LoaderManager;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.sql.ResultSet;
import java.util.ArrayList;

public class User_Details_Fetcher
{

    private static ResultSet resultSet;

    protected Void fetch_details(Void...voids){

        try{
            String command="select userid,email,sex from borrowers where userid=\"katari15045\" ";
            Database database = new Database(command, true);
            Thread dbThread = new Thread(database);
            dbThread.start();
            dbThread.join();
            //details=database.getResultSet();
            //Log(database.getResultSet());
            resultSet=database.getResultSet();
            System.out.println(resultSet);
            database.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
