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

public class OccupancyFragment extends Fragment {
    private View view = null;
    private static String title = null;

    public OccupancyFragment() {
        title = ((AppCompatActivity) MainActivity.getContext()).getResources().getString
                (R.string.nav_drawer_occupancy_title);
    }

    @Override
    public void onResume() {
        Log.d("SAK", "OccupancyFragment::onResume()");
        super.onResume();
        MainActivity.changeActionBarTitle(title);
        NavDrawer.hideItem(3);
    }

    @Override
    public void onPause() {
        Log.d("SAK", "OccupancyFragment::onPause()");
        super.onPause();
        NavDrawer.showItem(3);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("SAK", "OccupancyFragment::onCreateView()");
        view = inflater.inflate(R.layout.fragment_occupancy, null);
        return view;
    }

    public static String getTitle(){
        return title;
    }
}
