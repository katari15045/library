package com.github.katari15045.iiitdlibrary.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.Activity.MainActivity;
import com.github.katari15045.iiitdlibrary.Helper.Global;
import com.github.katari15045.iiitdlibrary.R;

// Login Fragment that contains fields for username, password and a login button
public class LoginFragment extends Fragment {

    private View view = null;
    private AppCompatActivity activity = null;
    private EditText editTextUsername = null;
    private EditText editTextPassword = null;
    private Button buttonLogin = null;
    private static String title = null;

    public LoginFragment(){
        super();
        title = Global.context.getResources().getString(R.string.login_fragment_title);
    }

    @Override
    public void onResume() {
        Log.d("SAK", "LoginFragment::onResume()");
        super.onResume();
        Global.changeActionBarTitle(title);
    }

    // Inflates the fragment, Changes the action bar's title & collects all the views
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("SAK", "LoginFragment::onCreateView()");
        this.view = inflater.inflate(R.layout.fragment_login, container, false);
        this.activity = (AppCompatActivity)view.getContext();
        collectViews();
        return view;
    }

    private void collectViews(){
        editTextUsername = view.findViewById(R.id.fragment_login_edit_text_username);
        editTextPassword = view.findViewById(R.id.fragment_login_edit_text_password);
        buttonLogin = view.findViewById(R.id.fragment_login_button_login);
        buttonLogin.setOnClickListener(new LoginListener(view.getContext()));
    }

    public static String getTitle() {
        return title;
    }
}

// Listener for a click on Login button
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