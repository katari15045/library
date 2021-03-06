package com.github.katari15045.iiitdlibrary;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Saketh Katari on 27-02-2018.
 */
class Database implements Runnable{

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String command = null;
    private boolean isQuery;
    private static boolean isConnected = false;

    Database(String command, boolean isQuery){
        this.command = command;
        this.isQuery = isQuery;
    }

    @Override
    public void run() {
        try{
            Log.d("SAK", "Connecting...");
            if(!connect()){
                isConnected = false;
                return;
            }
            isConnected = true;
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
        }
    }

    private boolean connect(){
        try{
            String url = "jdbc:mysql://192.168.48.144:3306/lib";
            String username = "root";
            String password = "root";
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            Log.d("SAK", "Validating credentials...");
            connection = DriverManager.getConnection(url, username, password);
        }catch (Exception e) {
            Log.d("SAK", "Can't connect to DB");
            return  false;
        }
        return true;
    }

    void close(){
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

    static boolean isConnected(){
        return isConnected;
    }

    ResultSet getResultSet(){
        return resultSet;
    }
}
