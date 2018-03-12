package com.github.katari15045.iiitdlibrary.Fragment;

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
import com.github.katari15045.iiitdlibrary.R;

public class ResourcesFragment extends Fragment{

    private View view = null;
    private static String title = null;

    public ResourcesFragment(){
        title = ((AppCompatActivity)MainActivity.getContext()).getResources().getString
                (R.string.nav_drawer_resources_title);
    }

    @Override
    public void onResume() {
        Log.d("SAK", "ResourceFragment::onResume()");
        super.onResume();
        NavDrawer.hideItem(1);
        MainActivity.changeActionBarTitle(title);
    }

    @Override
    public void onPause() {
        Log.d("SAK", "ResourceFragment::onPause()");
        super.onPause();
        NavDrawer.showItem(1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("SAK", "ResourceFragment::onCreateView()");
        view = inflater.inflate(R.layout.fragment_resources, null);
        return  view;
    }

    public static String getTitle(){
        return title;
    }
}