package com.github.katari15045.iiitdlibrary.biblio;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.R;

public class BiblioCopiesFragment extends Fragment{

    private View view = null;
    private String debugTag = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_biblio_copies, container, false);
        debug();
        BiblioCopiesDataFetcher biblioCopiesDataFetcher = new BiblioCopiesDataFetcher(
                view.getContext());
        biblioCopiesDataFetcher.execute();
        return view;
    }

    private void debug(){
        debugTag = view.getResources().getString(R.string.debug_tag);
        Log.d(debugTag, "BiblioCopiesFrag::onCreateView()");
    }
}