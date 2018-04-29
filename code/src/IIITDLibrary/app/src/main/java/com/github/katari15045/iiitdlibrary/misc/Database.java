package com.github.katari15045.iiitdlibrary.misc;

import android.content.Context;
import android.util.Log;

import com.github.katari15045.iiitdlibrary.R;

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
    private Context context = null;
    private String degubTag = null;

    public Database(Context context, String command, boolean isQuery){
        this.command = command;
        this.isQuery = isQuery;
        this.context = context;
        degubTag = context.getResources().getString(R.string.debug_tag);
    }

    // Connects to a Database and executes a query or an update
    @Override
    public void run() {
        try{
            Log.d(degubTag, "Database::Connecting...");
            if(!connect()){
                // Couldn't connect to the database
                Log.d(degubTag, "Database::couldn't connect to DB!");
                isConnected = false;
                return;
            }
            // Connected to the Database
            isConnected = true;
            Log.d(degubTag, "Connected");
            preparedStatement = connection.prepareStatement(command);
            if(isQuery){
                // Execute te Query
                Log.d(degubTag, "executing query...");
                resultSet = preparedStatement.executeQuery();
                Log.d(degubTag, "Parsing result...");
            }else{
                // Execute the update
                Log.d(degubTag, "executing update...");
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
            Log.d(degubTag, "Database::Validating credentials...");
            connection = DriverManager.getConnection(url, username, password);
        }catch (Exception e) {
            Log.d(degubTag, "Can't connect to DB");
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
