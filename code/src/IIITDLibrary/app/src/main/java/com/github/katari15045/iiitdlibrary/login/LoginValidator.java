package com.github.katari15045.iiitdlibrary.login;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.MainActivity;
import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.misc.Universal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginValidator extends AsyncTask<Void, Void, Void> {

    private Context context = null;
    private String username = null;
    private String password = null;
    private boolean loggedIn = false;
    private String debugTag = null;
    // A user is logged in if majority i.e atleast 4 words are present in HTML response!!! :)
    private String loggedInData[] = {"welcome", "hello", "logout", "click here if you're not",
            "change your password", "your fines", "your library home"};

    public LoginValidator(Context context, String username, String password){
        this.context = context;
        this.username = username;
        this.password = password;
        debugTag = context.getString(R.string.debug_tag);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(debugTag, "LoginValidator::doInBackground()");
        String htmlPage = getHtmlPage().toLowerCase();
        Log.d(debugTag, "LoginValidator::doInBackground() got HTML Page!");
        String curStr = null;
        int count = 0, matches = 0;
        while(count < loggedInData.length){
            curStr = loggedInData[count];
            if(htmlPage.contains(curStr)){
                matches = matches+1;
            }
            count = count+1;
        }
        if(matches > (loggedInData.length/2)){
            loggedIn = true;
            Log.d(debugTag, "LoginValidator::doInBackground() Logged in!");
        }
        return null;
    }

    private String getHtmlPage(){
        try{
            String urlStr = "http://library.iiitd.edu.in/cgi-bin/koha/opac-user.pl";
            URL url = new URL(urlStr);
            String usernameEncoded = URLEncoder.encode(username, "UTF-8");
            String passwordEncoded = URLEncoder.encode(password, "UTF-8");
            String urlParams = "userid=" + usernameEncoded + "&password=" + passwordEncoded;
            byte[] urlParamBytes = urlParams.getBytes("UTF-8");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(urlParamBytes.length));
            connection.setDoOutput(true);
            connection.getOutputStream().write(urlParamBytes);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                stringBuilder.append((char)c);
            }
            return stringBuilder.toString();
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(loggedIn){
            Toast.makeText(context, context.getString(R.string.logged_in), Toast.LENGTH_SHORT).
                    show();
            LoginActivity.loggedIn = true;
            Universal.postLogin(context);
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }else{
            Toast.makeText(context, context.getString(R.string.invallid_details),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
