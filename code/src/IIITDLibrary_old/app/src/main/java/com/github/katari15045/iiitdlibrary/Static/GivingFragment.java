package com.github.katari15045.iiitdlibrary.Static;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.katari15045.iiitdlibrary.Main.NavDrawer;
import com.github.katari15045.iiitdlibrary.Misc.Global;
import com.github.katari15045.iiitdlibrary.R;

public class GivingFragment extends Fragment {
    private View view = null;
    private static String title = null;

    public GivingFragment() {
        title = ((AppCompatActivity) Global.context).getResources().getString
                (R.string.nav_drawer_giving_title);
    }

    @Override
    public void onResume() {
        Log.d("SAK", "GivingFragment::onResume()");
        super.onResume();
        Global.changeActionBarTitle(title);
        NavDrawer.hideItem(4);
    }

    @Override
    public void onPause() {
        Log.d("SAK", "GivingFragment::onPause()");
        super.onPause();
        NavDrawer.showItem(4);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("SAK", "GivingFragment::onCreateView()");
        view = inflater.inflate(R.layout.fragment_giving, null);
        return view;
    }

    public static String getTitle(){
        return title;
    }
}
