package com.github.katari15045.iiitdlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.katari15045.iiitdlibrary.Util.UsernameUtil;

public class LoginActivity extends AppCompatActivity {

    private  EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //email = (EditText)findViewById(R.id.email_edittext_login);
        //password = (EditText) findViewById(R.id.password_edittext_login);
        //Button loginButton = (Button) findViewById(R.id.login_button);

/*
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
                String[] temp = email.getText().toString().split("@iiitd");

                final String emailidtext = temp[0];

                String passwordtext = password.getText().toString();

                UsernameUtil.username = emailidtext;
                Log.i(LoginActivity.this+"", "fffffffffffffffffffff - "+UsernameUtil.username +"  " + emailidtext);
                startActivity(mainActivityIntent);
            }
        });
*/
    }
}
