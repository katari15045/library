package com.github.katari15045.iiitdlibrary.Biblio;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.katari15045.iiitdlibrary.Misc.Global;
import com.github.katari15045.iiitdlibrary.R;

import java.util.HashSet;

public class BiblioOverviewFragment extends Fragment {

    private View view = null;
    private static String title = "Book";
    public static String biblioNumber = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("SAK", "BiblioOverviewFragment::onCreateView()");
        view = inflater.inflate(R.layout.fragment_biblio_overview, null);
        Log.d("SAK", "BiblioIverviewFragment::biblioNumber -> " + biblioNumber);
        BiblioDataFetcher biblioDataFetcher = new BiblioDataFetcher(view);
        biblioDataFetcher.execute();
        return view;
    }

    @Override
    public void onResume() {
        Log.d("SAK", "BiblioOverviewFragment::onResume()");
        super.onResume();
        Global.changeActionBarTitle(title);
        Global.currentFragment = this;
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
