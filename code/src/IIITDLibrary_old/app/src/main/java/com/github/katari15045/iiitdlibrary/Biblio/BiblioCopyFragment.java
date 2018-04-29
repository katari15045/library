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

public class BiblioCopyFragment extends Fragment {

    private View view = null;
    private static String title = "Book";

    @Override
    public void onResume() {
        Log.d("SAK", "BiblioCopyFragment::onResume()");
        super.onResume();
        Global.changeActionBarTitle(title);
        Global.currentFragment = this;
        MyTabLayout.showTabLayout();
        MyTabLayout.init(1);
    }

    @Override
    public void onPause() {
        Log.d("SAK", "BiblioCopyFragment::onPause()");
        super.onPause();
        MyTabLayout.hideTabLayout();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("SAK", "BiblioCopyFragment::onCreateView()");
        view = inflater.inflate(R.layout.fragment_biblio_copy, null);
        return view;
    }

    public static String getTitle(){
        return title;
    }
}
