package com.github.katari15045.iiitdlibrary.login;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.Util.UsernameUtil;

public class LoginListener implements View.OnClickListener{

    private Context context = null;
    private String username = null;
    private String password = null;

    public LoginListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        username = LoginActivity.editTextUname.getText().toString();
        password = LoginActivity.editTextPass.getText().toString();
        UsernameUtil.username = username;
        if(username.isEmpty()){
            Toast.makeText(context, context.getString(R.string.empty_username), Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if(password.isEmpty()){
            Toast.makeText(context, context.getString(R.string.empty_password), Toast.LENGTH_LONG)
                    .show();
            return;
        }
        LoginValidator loginValidator = new LoginValidator(context, username, password);
        loginValidator.execute();
    }
}
