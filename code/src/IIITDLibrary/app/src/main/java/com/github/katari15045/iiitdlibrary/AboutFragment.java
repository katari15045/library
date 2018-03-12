package com.github.katari15045.iiitdlibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutFragment extends Fragment{

    private View view = null;
    private static String title = null;

    public AboutFragment(){
        title = MainActivity.getContext().getResources().getString(R.string.about_fragment_title);
    }

    @Override
    public void onResume() {
        super.onResume();
        NavDrawer.hideItem(0);
    }

    @Override
    public void onPause() {
        super.onPause();
        NavDrawer.showItem(0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about, null);
        return view;
    }

    static String getTitle(){
        return title;
    }
}