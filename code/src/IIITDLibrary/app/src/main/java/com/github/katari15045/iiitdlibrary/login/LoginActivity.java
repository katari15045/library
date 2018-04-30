package com.github.katari15045.iiitdlibrary.login;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.katari15045.iiitdlibrary.MainActivity;
import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.misc.Universal;

public class LoginActivity extends AppCompatActivity {

    private String debugTag = null;
    private DrawerLayout drawerLayout = null;
    static EditText editTextUname = null;
    static EditText editTextPass = null;
    private Button butLogin = null;
    public static boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        debugTag = getResources().getString(R.string.debug_tag);
        Log.d(debugTag, "LoginActivity::onCreate()");
        captureViews();
        Universal.initNavDrawer(this, drawerLayout);
        Universal.initBottomNavView(this);
        Universal.initStatusBar(this);
    }

    @Override
    protected void onResume() {
        Log.d(debugTag, "LoginActivity::onResume()");
        super.onResume();
        if(loggedIn){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        getSupportActionBar().setTitle(R.string.login);
    }

    private void captureViews(){
        drawerLayout = findViewById(R.id.activity_login_drawer_layout);
        editTextUname = findViewById(R.id.activity_login_edittext_username);
        editTextPass = findViewById(R.id.activity_login_edittext_password);
        butLogin = findViewById(R.id.activity_login_button_login);
        butLogin.setOnClickListener(new LoginListener(this));
    }
}