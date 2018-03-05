package com.github.katari15045.iiitdlibrary;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    private View view = null;
    private AppCompatActivity activity = null;
    private EditText editTextUsername = null;
    private EditText editTextPassword = null;
    private Button buttonLogin = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("SAK", "cur_frag = " + toString());
        this.view = inflater.inflate(R.layout.fragment_login, container, false);
        this.activity = (AppCompatActivity)view.getContext();
        changeActionBarTitle();
        collectViews();
        return view;
    }

    private void changeActionBarTitle(){
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle(view.getResources().getString(R.string.login_fragment_title));
    }

    private void collectViews(){
        editTextUsername = view.findViewById(R.id.fragment_login_edit_text_username);
        editTextPassword = view.findViewById(R.id.fragment_login_edit_text_password);
        buttonLogin = view.findViewById(R.id.fragment_login_button_login);
        buttonLogin.setOnClickListener(new LoginListener(view.getContext()));
    }

    @Override
    public String toString() {
        return "LoginFragment";
    }
}

class LoginListener implements View.OnClickListener{

    private Context context = null;

    LoginListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(context, "Login", Toast.LENGTH_SHORT).show();
        /* If login is successful -
        BottomNavigationView bottomNavigationView = BottomNavBar.getBottomNavBar();
        bottomNavigationView.getMenu().getItem(2).setTitle(context.getResources().
                getString(R.string.bottom_nav_bar_title_profile));*/
    }
}