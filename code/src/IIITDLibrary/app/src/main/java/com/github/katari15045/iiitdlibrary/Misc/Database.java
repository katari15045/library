package com.github.katari15045.iiitdlibrary.Misc;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Saketh Katari on 27-02-2018.
 */

// Close the connection from the caller end manually after parsing the resultset
public class Database implements Runnable{

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String command = null;
    private boolean isQuery;
    private static boolean isConnected = false;

    public Database(String command, boolean isQuery){
        this.command = command;
        this.isQuery = isQuery;
    }

    // Connects to a Database and executes a query or an update
    @Override
    public void run() {
        try{
            Log.d("SAK", "Connecting...");
            if(!connect()){
                // Couldn't connect to the database
                isConnected = false;
                return;
            }
            // Connected to the Database
            isConnected = true;
            Log.d("SAK", "Connected");
            preparedStatement = connection.prepareStatement(command);
            if(isQuery){
                // Execute te Query
                Log.d("SAK", "executing query...");
                resultSet = preparedStatement.executeQuery();
                Log.d("SAK", "Parsing result...");
            }else{
                // Execute the update
                Log.d("SAK", "executing update...");
                preparedStatement.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Connectes to a database and returns whether it is connected or not
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

    // Closes the resources
    public void close(){
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

    public static boolean isConnected(){
        return isConnected;
    }

    public ResultSet getResultSet(){
        return resultSet;
    }
}
