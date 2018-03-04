package com.github.katari15045.bookimagefromdb;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * Created by Saketh Katari on 27-02-2018.
 */

class Database extends AsyncTask<Void, Void, Void>{

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Context context = null;
    private AppCompatActivity activity = null;
    private AlertDialog dialog = null;
    private String command = null;
    private boolean isQuery;
    AsyncTask<Void, Void, Void> postExecTask = null;

    Database(Context context, String command, boolean isQuery){
        this.context = context;
        this.command = command;
        this.isQuery = isQuery;
        activity = (AppCompatActivity)context;
        initProgressDialog();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            Log.d("SAK", "Connecting...");
            connect();
            Log.d("SAK", "Connected");
            preparedStatement = connection.prepareStatement(command);
            if(isQuery){
                Log.d("SAK", "executing query...");
                resultSet = preparedStatement.executeQuery();
                Log.d("SAK", "Parsing result...");
            }else{
                Log.d("SAK", "executing update...");
                preparedStatement.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(postExecTask != null){
            postExecTask.execute();
        }
        dialog.dismiss();
    }

    public void setPostExecTask(AsyncTask<Void, Void, Void> task){
        this.postExecTask = task;
    }

    private void initProgressDialog(){
        View progressDialogView = activity.getLayoutInflater().inflate(R.layout.progress_dialog, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setView(progressDialogView);
        dialog = dialogBuilder.create();
    }

    private void connect(){
        try{
            String url = "jdbc:mysql://192.168.48.144:3306/lib";
            String username = "root";
            String password = "root";
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            Log.d("SAK", "Validating credentials...");
            connection = DriverManager.getConnection(url, username, password);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void close(){
        try{
            if(connection != null){
                connection.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String parseResultSet(){
        StringBuilder stringBuilder = new StringBuilder();
        try{
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int curCol = 1;
            int totalCols = resultSetMetaData.getColumnCount();
            while(curCol <= totalCols){
                stringBuilder.append(resultSetMetaData.getColumnName(curCol));
                if(curCol != totalCols){
                    stringBuilder.append("|");
                }
                curCol = curCol+1;
            }
            stringBuilder.append("\n\n");
            while (resultSet.next()){
                curCol = 1;
                while ((curCol <= totalCols)){
                    if(curCol == 1){
                        stringBuilder.append("=>");
                    }
                    stringBuilder.append(resultSet.getString(curCol));
                    if(curCol != totalCols){
                        stringBuilder.append("|");
                    }
                    curCol = curCol+1;
                }
                stringBuilder.append("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  stringBuilder.toString();
    }

    ResultSet getResultSet(){
        return resultSet;
    }
}
