package com.github.katari15045.iiitdlibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.katari15045.iiitdlibrary.Activity.MainActivity;
import com.github.katari15045.iiitdlibrary.Gui.NavDrawer;

public class ServicesFragment extends Fragment {

    private View view = null;
    private static String title = null;

    public ServicesFragment(){
        title = MainActivity.getContext().getResources().
                getString(R.string.nav_drawer_services_title);
    }

    @Override
    public void onResume() {
        Log.d("SAK", "ServicesFragment::onResume()");
        super.onResume();
        NavDrawer.hideItem(2);
        ((AppCompatActivity)MainActivity.getContext()).getSupportActionBar().setTitle(title);
    }

    @Override
    public void onPause() {
        Log.d("SAK", "ServicesFragment::onPause()");
        super.onPause();
        NavDrawer.showItem(2);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("SAK", "ServicesFragment::onCreateView()");
        view = inflater.inflate(R.layout.fragment_services, null);
        return view;
    }

    public static String getTitle(){
        return title;
    }
}