package com.github.katari15045.iiitdlibrary.Fragment.Biblio;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.katari15045.iiitdlibrary.Activity.MainActivity;
import com.github.katari15045.iiitdlibrary.R;

public class BiblioOverviewFragment extends Fragment {

    private View view = null;
    private static String title = "Book";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("SAK", "BiblioOverviewFragment::onCreateView()");
        view = inflater.inflate(R.layout.fragment_biblio_overview, null);
        return view;
    }

    @Override
    public void onResume() {
        Log.d("SAK", "BiblioOverviewFragment::onResume()");
        super.onResume();
        MainActivity.changeActionBarTitle(title);
        MainActivity.currentFragment = this;
        MyTabLayout.showTabLayout();
        MyTabLayout.init(0);
    }

    @Override
    public void onPause() {
        Log.d("SAK", "BiblioOverviewFragment::onPause()");
        super.onPause();
        MyTabLayout.hideTabLayout();
    }

    public static String getTitle(){
        return title;
    }
}
